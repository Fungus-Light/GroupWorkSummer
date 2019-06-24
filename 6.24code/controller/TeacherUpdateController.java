//package com.example.demo.controller;
//
//
//import com.example.demo.entity.Teacher;
//import com.example.demo.entity.Topicset;
//import com.example.demo.service.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
////import java.net.http.HttpResponse;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
//@Controller
//
////@RequestMapping("/update")
//public class TeacherUpdateController {
//    @Autowired
//    private TeacherService ts;
//    @RequestMapping("/TeacherPasUpdate")
//    public String UpdateTeacherInfo(HttpSession session, @RequestParam("password") String m, HttpServletResponse r){
//
//        String id=(String)session.getAttribute(WebSecurityConfig.SESSION_KEY);
//        ts.updateTeacherPassword(id,m);
//        return "Success";
//    }
//    @RequestMapping("/TeacherTelUpdate")
//    public String UpadateTeacherPassword(HttpSession session,@RequestParam("Tel") String p,HttpServletResponse r){
//        String id=(String)session.getAttribute(WebSecurityConfig.SESSION_KEY);
//        ts.updateTeacherTel(id,p);
//        return "Success";
//    }
//    @RequestMapping("/SetTopic")
//    public String SetTopic(HttpSession session,@RequestParam("Topic") String p,HttpServletResponse r)
//    {
//     Teacher t=ts.findTeacher((String)session.getAttribute(WebSecurityConfig.SESSION_KEY));
//      ts.SetTopic(t,p);
//      return "Success";
//    }
//
//
//
//}
