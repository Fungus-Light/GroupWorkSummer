package com.minqing.demo.service;

import com.minqing.demo.entity.Topic;
import com.minqing.demo.entity.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    public List<Topic> findAllTopic(){return topicRepository.findAll();}

    public List<Topic> findUnexamedTopic(){
        return topicRepository.findByState(0);
    }
    public void passTopic(Integer id){
    Topic tp=topicRepository.findById(id).get();
    tp.setState(1);
    topicRepository.save(tp);
    }
}
