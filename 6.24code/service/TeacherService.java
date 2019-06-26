package com.example.demo.service;

import com.example.demo.entity.*;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository tr;
    @Autowired
    private userRepository ur;
    @Autowired
    private TopicsetRepository pr;

   public int updateTeacherTel(String teacherid,String newTel)
   {
       Optional op=tr.findById(teacherid);
       Teacher t=(Teacher)op.get();
       t.setTel(newTel);
       System.out.println(t.getAcademic()+t.getTel()+t.getName()+t.getUserid());
       tr.save(t);
       return 0;

   }
   public int updateTeacherPassword(String teacherid,String newpassword)

   {
        Optional op=ur.findById(teacherid);
        user u=(user)op.get();
        u.setPassword(newpassword);
        ur.save(u);
        return 0;
   }
   public Teacher findTeacher(String id)
   {
       Optional op=tr.findById(id);
       return (Teacher)op.get();
   }
   public int SetTopic(Teacher t,String topic)
   {
       String userid=t.getUserid();
       Topicset tp=new Topicset();
       tp.setUserid(userid);
       tp.setTopic(topic);
       tp.setDescription("");
       pr.save(tp);
       return 0;
   }
}
