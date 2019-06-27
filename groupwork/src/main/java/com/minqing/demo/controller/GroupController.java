package com.minqing.demo.controller;

import com.minqing.demo.service.TopicgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Group
{
    public List<String> teacherid;
    public List<String> studentid;
    public
    String groupid;
}
@RestController
public class GroupController {
    @Autowired
    private TopicgroupService topicgroupService;
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
    @RequestMapping("/showGroup")
    @ResponseBody
    public Group[] showGroup(){
         List<String> groupids=topicgroupService.findAllgroupId();
         int length=groupids.size();
         Group []g=new Group[length];
         for(int i=0;i<length;i++)
         {
             g[i].groupid=groupids.get(i);
            g[i].studentid=topicgroupService.findStudentIdbyGroup(g[i].groupid);
            g[i].teacherid=topicgroupService.findTeachbyGroup(g[i].groupid);
         }

         return g;

    }
}
