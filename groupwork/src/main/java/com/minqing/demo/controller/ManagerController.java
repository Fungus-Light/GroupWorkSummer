package com.minqing.demo.controller;

import com.minqing.demo.entity.Manager;
import com.minqing.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ManagerController {
    @Autowired
    ManagerService managerService;

    @RequestMapping("/showManager")
    public List showManager(){
        List<Manager> list = managerService.findManagers();
        int length = list.size();
        List<Map<String,String>> newlist = new ArrayList<>(length);
        for(int i=0;i<length;i++){
            Map<String,String> map = new HashMap<>();
            map.put("userid",list.get(i).getUserid());
            map.put("name",list.get(i).getName());
            map.put("tel",list.get(i).getTel());
            newlist.add(map);
        }
        return newlist;
    }
}
