package com.minqing.demo.service;


import com.minqing.demo.entity.Topicgroup;
import com.minqing.demo.entity.TopicgroupRepository;
import org.springframework.stereotype.Service;

@Service
public class TopicgroupService {
    private TopicgroupRepository topicgroupRepository;


    public void addTopicgroup(String userid,String groupid,int identity)
    {
        Topicgroup topicgroup=new Topicgroup();
        topicgroup.setGroupid(groupid);
        topicgroup.setUserid(userid);
        topicgroup.setIdentity(identity);
        topicgroupRepository.save(topicgroup);
    }







}
