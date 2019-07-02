package com.minqing.demo.controller;

import com.minqing.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/addMessage")
    public void addMessage(@RequestBody Map<String,String> map){
        String senderid = map.get("senderid");
        String userid = map.get("userid");
        String title = map.get("title");
        String content = map.get("content");
        if(!senderid.equals(userid)){
            messageService.addMessage(senderid,userid,title,content);
        }
    }


}
