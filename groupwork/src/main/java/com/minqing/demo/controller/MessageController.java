package com.minqing.demo.controller;

import com.minqing.demo.entity.Message;
import com.minqing.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/addMessage")
    public void addMessage(@RequestBody Map<String,String> map){
        String title = map.get("title");
        String content = map.get("content");
        messageService.addMessage(title,content);
    }

    @ResponseBody
    @RequestMapping("/showMessage")
    public List<Message> showMessage(){
        return messageService.findAllMessages();
    }
}
