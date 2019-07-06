package com.minqing.demo.controller;

import com.minqing.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CookieController {
    @Autowired
    private UserService userService;

    @RequestMapping("/checkCookie")
    public int checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userid = "";
        if(cookies == null) return 5;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        if(userid.equals(""))  return 5;
        return userService.findById(userid).getTitle();
    }
}
