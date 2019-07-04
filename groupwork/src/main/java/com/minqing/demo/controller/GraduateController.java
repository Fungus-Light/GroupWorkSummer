package com.minqing.demo.controller;

import com.minqing.demo.entity.Graduate;
import com.minqing.demo.entity.Student;
import com.minqing.demo.service.GraduateService;
import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.StudentService;
import com.minqing.demo.service.TeacherService;
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
public class GraduateController {
    @Autowired
    GraduateService graduateService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    ManagerService managerService;





    @RequestMapping("/sendApply")
    public void sendApply(@RequestBody Map<String,String> m)
    {
        graduateService.sendApply(m.get("studentid"),m.get("studentname"),m.get("finishtime"),
                m.get("topicname"),m.get("teachername"),m.get("func"));
    }

    @RequestMapping("/showHasApplied")
    public int showHasApplied(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        return graduateService.hasApplied(userid);
    }

    @RequestMapping("/showWhoAgreed")
    public int showWhoAgreed(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        return graduateService.whoAgreed(userid);
    }

    @RequestMapping("/agreedTeacher")
    public void agreedTeacher(@RequestBody Map<String,String> m)
    {
        String studentid=m.get("studentid");
        graduateService.TeacherAgreed(studentid);
    }

    @RequestMapping("/agreedManager")
    public void agreedManager(@RequestBody Map<String,String> m)
    {
        String studentid=m.get("studentid");
        graduateService.ManagerAgreed(studentid);
    }

    @RequestMapping("/showAppliedStudentsTeacher")
    public List showAppliedStudentsTeacher(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        String teachername=teacherService.findTeacherNameByTeacherId(userid);
        List<String>  studentids=graduateService.findStudentidsByTeacherName(teachername);
        List<Graduate> graduates=new ArrayList<>();
        int length=studentids.size();
        for(int i=0;i<length;i++)
        {
            Graduate g=graduateService.findById(studentids.get(i));
            graduates.add(g);
        }
        return graduates;

    }



    @RequestMapping("/showAppliedStudentsManager")
    public List showAppliedStudentsManager(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        String academic=managerService.findAcademic(userid);
        List<String>  studentids=graduateService.findStudentidsByTeacherName(academic);
        List<Graduate> graduates=new ArrayList<>();
        int length=studentids.size();
        for(int i=0;i<length;i++)
        {
            Graduate g=graduateService.findById(studentids.get(i));
            graduates.add(g);
        }
        return graduates;

    }
}
