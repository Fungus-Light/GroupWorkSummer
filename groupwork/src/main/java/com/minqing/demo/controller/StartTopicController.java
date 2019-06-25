package com.minqing.demo.controller;

import com.minqing.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StartTopicController {
    @Autowired
    private ManagerService managerService;
    public void setSegment(@RequestBody Map<String,Integer> m)
    {
        managerService.SetSegment((int)m.get("segid"),(int)m.get("status"));
    }
}
