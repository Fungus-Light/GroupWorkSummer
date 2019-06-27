package com.minqing.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CookieController {

    @RequestMapping("/checkCookie")
    public int checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userid = "";
        if(cookies == null) return 0;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        if(userid.equals(""))  return 0;
        return 1;
    }
}
