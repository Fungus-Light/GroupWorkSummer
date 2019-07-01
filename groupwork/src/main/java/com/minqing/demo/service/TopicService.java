package com.minqing.demo.service;

import com.minqing.demo.entity.Topic;
import com.minqing.demo.entity.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List findAllTopicByAcademic(String academic){
        List<Integer> topicids=topicRepository.findTopicidByAcademic(academic);
        List<Topic> topics=new ArrayList<>();
        for(Integer s:topicids)
        {
            Topic t=topicRepository.findById(s).get();
            topics.add(t);
        }
        return topics;
    }

    public List<Topic> findAllTopic(){
        return topicRepository.findAll();
    }

    public List<Topic> findAllAvaliableTopic(){
        return topicRepository.findByState(1);
    }

    public Topic findTopicById(int topicid){
        return topicRepository.findById(topicid).get();
    }

}
