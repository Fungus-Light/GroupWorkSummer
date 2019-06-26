package com.minqing.demo.controller;


import com.minqing.demo.service.TopicService;
import com.minqing.demo.service.TopicgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StartTitleController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicgroupService topicgroupService;


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
    @RequestMapping("/divideGroup")
    public void divideGroup(@RequestBody List<List<Map<String,String>>> l)
    {
        List<String> teacherid=new ArrayList<>();
        List<String> studentid=new ArrayList<>();
        String groupid=new String();
        for(List<Map<String,String>> li:l)
        {
            for(Map<String,String> m:li)
            {
                if(m.get("groupid")==null) {
                    if (m.get("teacherid") == null) {
                        studentid.add(m.get("studentid"));
                    }
                    else
                        teacherid.add(m.get("studentid"));
                }
                else groupid=m.get("groupid");

            }
        }
        for(String s:studentid) {
            topicgroupService.addStudent(s, groupid);
        }
        for(String s:teacherid)
        {
            topicgroupService.addTeacher(s,groupid);
        }

    }
}
