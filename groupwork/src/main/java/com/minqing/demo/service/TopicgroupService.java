package com.minqing.demo.service;


import com.minqing.demo.entity.Topicgroup;
import com.minqing.demo.entity.TopicgroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicgroupService {
    @Autowired
    private TopicgroupRepository topicgroupRepository;


    public void addTopicgroup(String userid,String groupid,int identity)
    {
        Topicgroup topicgroup=new Topicgroup();
        topicgroup.setGroupid(groupid);
        topicgroup.setUserid(userid);
        topicgroup.setIdentity(identity);
        topicgroupRepository.save(topicgroup);
    }

    public String findGropuidByUserid(String userid){
        return topicgroupRepository.findGropuidByUserid(userid);
    }
    public List findTopicgroupByGroupid(String groupid){
        return topicgroupRepository.findTopicgroupByGroupid(groupid);
    }







}
