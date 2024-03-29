package com.minqing.demo.controller;

import com.minqing.demo.entity.SelectTopic;
import com.minqing.demo.entity.Teacher;
import com.minqing.demo.entity.Topic;
import com.minqing.demo.service.SelectTopicService;
import com.minqing.demo.service.TeacherService;
import com.minqing.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StartTitleController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private SelectTopicService selectTopicService;
    @Autowired
    private TeacherService teacherService;



    @RequestMapping("/addTopic")
    public void addTopic(@RequestBody Map<String,String> map, HttpServletRequest request){
        String topic = map.get("topic");
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
//        //String userid = map.get("userid");
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
    public List<Map<String,Object>> showTeacherTopic(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
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
        List<Topic> list = topicService.findAllTopic();
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


    @RequestMapping("/showAvailableTopic")
    public List showAvaliableTopic(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String studentid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                studentid = cookie.getValue();
            }
        }
        List<Map<String,Object>> list = new ArrayList<>();
        if(selectTopicService.hasSelected(studentid)){
            SelectTopic selectTopic = selectTopicService.findSelectTopicByStudentId(studentid);
            Teacher teacher = teacherService.findTeacher(selectTopic.getTeacherid());
            Topic topic = topicService.findTopicById(selectTopic.getTopicid());
            Map<String,Object> map = new HashMap<>();
            map.put("topic",topic.getTopic());
            map.put("topicid",topic.getTopicid());
            map.put("description",topic.getDescription());
            map.put("academic",teacher.getAcademic());
            map.put("name",teacher.getName());
            list.add(map);
        }
        else{
            List<Topic> newlist = topicService.findAllAvaliableTopic();
            int length = newlist.size();
            for(int i=0;i<length;i++){
                Map<String,Object> map = new HashMap<>();
                Topic topic = newlist.get(i);
                Teacher teacher = teacherService.findTeacher(newlist.get(i).getUserid());
                map.put("topic",topic.getTopic());
                map.put("topicid",topic.getTopicid());
                map.put("description",topic.getDescription());
                map.put("academic",teacher.getAcademic());
                map.put("name",teacher.getName());
                list.add(map);
            }
        }
        return list;
    }

    @RequestMapping("/selectTopic")
    public void selectTopic(@RequestBody Map<String,Object> m,HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String studentid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                studentid = cookie.getValue();
            }
        }
        int topicid=(int)m.get("topicid");
//        String teacherid=(String)m.get("teacherid");
        Topic topic = topicService.findTopicById(topicid);
        selectTopicService.addSelectTopic(topicid,studentid,topic.getUserid());
    }
}
