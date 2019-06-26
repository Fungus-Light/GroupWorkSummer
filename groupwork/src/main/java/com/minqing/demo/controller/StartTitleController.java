package com.minqing.demo.controller;

import com.minqing.demo.entity.Topic;
import com.minqing.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StartTitleController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("/addTopic")
    public void addTopic(@RequestBody Map<String,String> map){
        String topic = map.get("topic");
        String userid = map.get("userid");
        String description = map.get("description");
        topicService.addTopic(topic,userid,description);
    }

    @RequestMapping("/acceptTopic")
    public void acceptTopic(@RequestBody Map<String,Integer> map){
        int topicid = map.get("topicid");
        topicService.decideTopic(topicid,1);
    }

    @RequestMapping("/refuseTopic")
    public void refuseTopic(@RequestBody Map<String,Integer> map){
        int topicid = map.get("topicid");
        topicService.decideTopic(topicid,2);
    }

    @RequestMapping("/showTeacherTopic")
    public List<Map<String,Object>> showTeacherTopic(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        List<Topic> list = topicService.findTopicByTeacher(userid);
        int length = list.size();
        List<Map<String,Object>> newlist = new ArrayList<>();
        for(int i=0;i<length;i++){
            Map<String,Object> map1 = new HashMap<>();
            map1.put("topicid",list.get(i).getTopicid());
            map1.put("topic",list.get(i).getTopic());
            map1.put("userid",list.get(i).getUserid());
            map1.put("state",list.get(i).getState());
            map1.put("description",list.get(i).getDescription());
            newlist.add(map1);
        }
        return newlist;
    }

    @RequestMapping("/showAllTopic")
    public List<Map<String,Object>> showAllTopic(){
        List<Topic> list = topicService.findByState0();
        int length = list.size();
        List<Map<String,Object>> newlist = new ArrayList<>();
        for(int i=0;i<length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("topicid",list.get(i).getTopicid());
            map.put("topic",list.get(i).getTopic());
            map.put("userid",list.get(i).getUserid());
            map.put("state",list.get(i).getState());
            map.put("description",list.get(i).getDescription());
            newlist.add(map);
        }
        return newlist;
    }


}
