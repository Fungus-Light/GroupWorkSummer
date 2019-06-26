package com.minqing.demo.service;

import com.minqing.demo.entity.Topicgroup;
import com.minqing.demo.entity.TopicgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicgroupService {
    @Autowired
    TopicgroupRepository topicgroupRepository;
    public void saveGroup(String teacherid,String studentid)
    {
        Topicgroup topicgroup=new Topicgroup();
        topicgroup.setStudentid(studentid);
        topicgroup.setTeacherid(teacherid);
        topicgroupRepository.save(topicgroup);
    }
}
