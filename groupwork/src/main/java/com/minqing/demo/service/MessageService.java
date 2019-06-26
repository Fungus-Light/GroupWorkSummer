package com.minqing.demo.service;

import com.minqing.demo.entity.Message;
import com.minqing.demo.entity.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void addMessage(String userid,String content){
        Message message = new Message();
        message.setUserid(userid);
        message.setContent(content);
    }

    public List<Message> findMessageByUser(String userid){
        return messageRepository.findByUseridOrderByTime(userid);
    }

    public List<Message> findAllMessage(){

    }
}
