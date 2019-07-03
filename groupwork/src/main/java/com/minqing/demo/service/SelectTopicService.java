package com.minqing.demo.service;

import com.minqing.demo.entity.SelectTopic;
import com.minqing.demo.entity.SelectTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectTopicService {
    @Autowired
    private SelectTopicRepository selectTopicRepository;

    public void addSelectTopic(Integer topicid,String studentid,String teacherid) {
        SelectTopic selectTopic=new SelectTopic();
        selectTopic.setTopicid(topicid);
        selectTopic.setStudentid(studentid);
        selectTopic.setTeacherid(teacherid);
        selectTopicRepository.save(selectTopic);
    }

    public boolean hasSelected(String studentid){
        return selectTopicRepository.findById(studentid).isPresent();
    }

    public SelectTopic findSelectTopicByStudentId(String studentid){
        return selectTopicRepository.findById(studentid).get();
    }
    public boolean isSelectTopic(String studentid)
    {
        return selectTopicRepository.findById(studentid).isPresent();
    }
    public List<SelectTopic> findSelectTopicByTeacher(String teacherid){
        return selectTopicRepository.findSelectTopicsByTeacherid(teacherid);
    }
}
