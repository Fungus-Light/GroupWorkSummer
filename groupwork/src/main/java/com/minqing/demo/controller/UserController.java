package com.minqing.demo.controller;

import com.minqing.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestBody Map<String,String> map, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String userid = map.get("userid");
        String password = map.get("password");
        int number = userService.login(userid,password);
        if(number >= 3){
            return "index";
        }
        else{
            return "404";
        }
    }
}
