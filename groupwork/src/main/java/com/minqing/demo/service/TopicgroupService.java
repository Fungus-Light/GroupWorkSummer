package com.minqing.demo.service;


import com.minqing.demo.entity.Topicgroupstu;
import com.minqing.demo.entity.TopicgroupstuRepository;
import com.minqing.demo.entity.Topicgrouptch;
import com.minqing.demo.entity.TopicgrouptchRepository;
import org.springframework.beans.factory.ListableBeanFactoryExtensionsKt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> findTeachbyGroup(String groupid)
    {
        List<Topicgrouptch> s= topicgroupforteacherRepository.findByGroupid(groupid);
        List<String> teacherid=new ArrayList<>();
        for(Topicgrouptch temp:s)
        {
            teacherid.add(temp.getTeacherid());
        }
        return teacherid;
    }
    public List<String> findStudentIdbyGroup(String groupid)
    {
        List<Topicgroupstu> s= topicgroupforstudentRepository.findByGroupid(groupid);
        List<String> studentid=new ArrayList<>();
        for(Topicgroupstu temp:s)
        {
            studentid.add(temp.getStudentid());
        }
        return studentid;
    }
    public List<String> findAllgroupId()
    {
        return topicgroupforteacherRepository.findAllGroupid();
    }
}
