package com.minqing.demo.controller;

import com.minqing.demo.entity.Topicgroup;
import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.StudentService;
import com.minqing.demo.service.TeacherService;
import com.minqing.demo.service.TopicgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GroupController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TopicgroupService topicgroupService;





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


    @RequestMapping("/groupsetAcademic")
    public void groupsetAcademic(@RequestBody Map<String,Object> m )
    {

        Integer type=(int)m.get("type");
        String userid=(String)m.get("userid");
        String groupid= m.get("groupid").toString();

        topicgroupService.addTopicgroup(userid,groupid,type);
    }


    @RequestMapping("/showYourgroups")
    public List showYourgroups(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        String groupid=topicgroupService.findGropuidByUserid(userid);
        List<Topicgroup> topicgroups=topicgroupService.findTopicgroupByGroupid(groupid);
        return topicgroups;
    }


}
