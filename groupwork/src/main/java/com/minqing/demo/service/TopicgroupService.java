package com.minqing.demo.service;


import com.minqing.demo.entity.Topicgroupstu;
import com.minqing.demo.entity.TopicgroupstuRepository;
import com.minqing.demo.entity.Topicgrouptch;
import com.minqing.demo.entity.TopicgrouptchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicgroupService {
@Autowired
    private TopicgroupstuRepository topicgroupforstudentRepository;
@Autowired
    private TopicgrouptchRepository topicgroupforteacherRepository;

    public void addTeacher(String Teacherid,String groupid)
    {
        Topicgrouptch t=new Topicgrouptch();
        t.setGroupid(groupid);
        t.setTeacherid(Teacherid);
         topicgroupforteacherRepository.save(t);
    }
    public void addStudent(String Studentid,String groupid)
    {
        Topicgroupstu t=new Topicgroupstu();
        t.setGroupid(groupid);
        t.setStudentid(Studentid);
        topicgroupforstudentRepository.save(t);
    }
}
