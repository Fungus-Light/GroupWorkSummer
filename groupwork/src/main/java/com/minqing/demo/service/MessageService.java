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

    public void addMessage(String senderid,String userid,String title,String content){
        Message message = new Message();
        message.setSenderid(senderid);
        message.setUserid(userid);
        message.setTitle(title);
        message.setContent(content);

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        message.setTime(dateFormat.format(date));

        message.setState(1);//标记为未读
        messageRepository.save(message);
    }

    public List<Message> findMessageByUser(String userid){
        return messageRepository.findByUserid(userid);
    }

    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }

    public void haveReadMessage(int messageid){
        Message message = messageRepository.findById(messageid).get();
        message.setState(2);//标记为已读
        messageRepository.save(message);
    }
}
