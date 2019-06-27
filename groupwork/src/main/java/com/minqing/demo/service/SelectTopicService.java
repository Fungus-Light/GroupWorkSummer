package com.minqing.demo.service;

import com.minqing.demo.entity.SelectTopic;
import com.minqing.demo.entity.SelectTopicRepository;
import org.springframework.stereotype.Service;

@Service
public class SelectTopicService {
    SelectTopicRepository selectTopicRepository;
    public void addSelectTopic(Integer topicid,String studentid,String teacherid)
    {
        SelectTopic selectTopic=new SelectTopic();
        selectTopic.setTopicid(topicid);
        selectTopic.setStudentid(studentid);
        selectTopic.setTeacherid(teacherid);
        selectTopicRepository.save(selectTopic);
    }
}
