package com.minqing.demo.service;

import com.minqing.demo.entity.Message;
import com.minqing.demo.entity.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void addMessage(String title,String content){
        Message message = new Message();
        message.setTitle(title);
        message.setContent(content);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        message.setTime(dateFormat.format(date));

        messageRepository.save(message);
    }

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }
}
