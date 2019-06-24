package com.minqing.demo.controller;

import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    public int login(@RequestBody Map<String,String> map, HttpServletResponse response){
        String userid = map.get("userid");
        String password = map.get("password");
        int number = userService.login(userid,password);
        if(number >= 3){
            response.addCookie(new Cookie("userid",userid));
        }
        return number;
    }

    @PostMapping("/addManager")
    public void addManager(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        String password = map.get("password");
        String name = map.get("name");
        String tel = map.get("tel");
        userService.addUser(userid,password,1);
        managerService.addManager(userid,name,tel);
    }
}
