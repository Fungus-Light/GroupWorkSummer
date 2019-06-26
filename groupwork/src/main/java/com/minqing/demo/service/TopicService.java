package com.minqing.demo.service;

import com.minqing.demo.entity.Topic;
import com.minqing.demo.entity.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public void addTopic(String topic,String userid,String description){
        Topic topic1 = new Topic();
        topic1.setTopic(topic);
        topic1.setUserid(userid);
        topic1.setState(0);
        topic1.setDescription(description);
        topicRepository.save(topic1);
    }

    public void decideTopic(int topicid,int state){
        Topic topic = topicRepository.findById(topicid).get();
        topic.setState(state);
        topicRepository.save(topic);
    }

    public List<Topic> findTopicByTeacher(String userid){
        return topicRepository.findByUserid(userid);
    }

    public List<Topic> findByState0(){
        return topicRepository.findByState(0);
    }

    public List<Topic> findCompletedTopic(){
        return topicRepository.findByState(1);
    }
}
