//package com.example.demo.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.example.demo.config.WebSecurityConfig;
//import com.example.demo.entity.user;
//import com.example.demo.service.loginservice;
//
//import javax.servlet.http.HttpSession;
//import java.util.Map;
//
///**
// * Created by huangds on 2017/10/24.
// */
//@Controller
//public class logincontroller {
//
//    @Autowired
//    private loginservice ls;
//
//    @GetMapping("/")
//   public String index(){
//
//        return "index";
//   }
//
//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }
//
//        @PostMapping("/loginVerify")
//        public String loginVerify(@RequestParam("userid") String id,@RequestParam("password") String password, HttpSession session){
//            user user = new user();
//            user.setPassword(password);
//            user.setUserid(id);
//            boolean verify = ls.verifyLogin(user);
//            System.out.println(verify);
//            System.out.print(verify);
//        if (verify) {
//            session.setAttribute(WebSecurityConfig.SESSION_KEY, id);
//            System.out.println(id);
//            return "s1";
//        } else {
//            return "redirect:/login";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
//        return "redirect:/login";
//    }
//}
