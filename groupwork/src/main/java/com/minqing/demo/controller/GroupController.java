package com.minqing.demo.controller;

import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.StudentService;
import com.minqing.demo.service.TeacherService;
import com.minqing.demo.service.TopicgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Group
{
    public List<String> teacherid;
    public List<String> studentid;
    public String groupid;
}
@RestController
public class GroupController {
    @Autowired
    private TopicgroupService topicgroupService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/groupshowAcademicStudent")
    public List<List<String>> groupshowAcademicStudent(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        String academic = managerService.findAcademic(userid);//找到了管理员的学院

        List<String> students=studentService.findIdByAcademic(academic);//根据学院找到所有学生
        List<String> teachers=teacherService.findIdByAcademic(academic);//根据学院找到所有老师
        List<List<String>> data=new ArrayList<>();
        data.add(students);
        data.add(teachers);
        return data;
    }












    @RequestMapping("/divideGroup")
    public void divideGroup(@RequestBody List<List<Map<String,String>>> l)
    {
        List<String> teacherid=new ArrayList<>();
        List<String> studentid=new ArrayList<>();
        String groupid=new String();
        for(List<Map<String,String>> li:l)
        {
            for(Map<String,String> m:li)
            {
                if(m.get("groupid")==null) {
                    if (m.get("teacherid") == null) {
                        studentid.add(m.get("studentid"));
                    }
                    else
                        teacherid.add(m.get("studentid"));
                }
                else groupid=m.get("groupid");

            }
        }
        for(String s:studentid) {
            topicgroupService.addStudent(s, groupid);
        }
        for(String s:teacherid)
        {
            topicgroupService.addTeacher(s,groupid);
        }

    }
    @RequestMapping("/showGroup")
    @ResponseBody
    public Group[] showGroup(){
         List<String> groupids=topicgroupService.findAllgroupId();
         int length=groupids.size();
         Group []g=new Group[length];
         for(int i=0;i<length;i++)
         {
             g[i].groupid=groupids.get(i);
            g[i].studentid=topicgroupService.findStudentIdbyGroup(g[i].groupid);
            g[i].teacherid=topicgroupService.findTeachbyGroup(g[i].groupid);
         }

         return g;
    }
}
