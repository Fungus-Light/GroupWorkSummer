package com.minqing.demo.controller;

import com.minqing.demo.entity.Topic;
import com.minqing.demo.entity.TopicRepository;
import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StartTopicController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private TopicService topicService;

    public void setSegment(@RequestBody Map<String, Integer> m) {
        managerService.SetSegment((int) m.get("segid"), (int) m.get("status"));
    }

    public List<Topic> showTopic() {
        return topicService.findAllTopic();
    }

    public List<Topic> showUnexamedTopic() {
        return topicService.findUnexamedTopic();
    }

    public void examTopic(@RequestBody Map<String, Integer> m) {
        topicService.passTopic(m.get("topicid"));
    }
}
}
