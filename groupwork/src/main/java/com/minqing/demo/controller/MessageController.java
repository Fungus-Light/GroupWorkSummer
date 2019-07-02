package com.minqing.demo.controller;

import com.minqing.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;


    @RequestMapping("/sendMessage")
    public void sendMessage(@RequestBody Map<String,Object> m)
    {
        messageService.addMessage((String)m.get("title"),(String)m.get("content"));
    }

    @RequestMapping("/showMessage")
    public List showMessage()
    {
        return messageService.findAllMessages();
    }
//    @RequestMapping("/addMessage")
//    public void addMessage()
}
