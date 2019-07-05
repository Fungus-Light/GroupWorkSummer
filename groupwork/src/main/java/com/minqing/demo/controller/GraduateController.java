package com.minqing.demo.controller;

import com.minqing.demo.service.GraduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GraduateController {
    @Autowired
    GraduateService graduateService;
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


}
