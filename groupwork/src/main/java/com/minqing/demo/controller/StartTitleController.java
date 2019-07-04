package com.minqing.demo.controller;

import com.minqing.demo.entity.SelectTopic;
import com.minqing.demo.entity.Student;
import com.minqing.demo.entity.Teacher;
import com.minqing.demo.entity.Topic;
import com.minqing.demo.service.*;
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
    @Autowired
    private StudentService studentService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private PaperstateService paperstateService;


//教师用来出题
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

//管理员用来审核
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

//某个老师只能看见自己出的题目
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

//次学生只能看见对应学院的题目进行选取
    @RequestMapping("/showAcademicTopicStudent")
    public List<Map<String,Object>> showAcademicTopicStudent(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        boolean hasselected=selectTopicService.hasSelected(userid);
        List<Map<String,Object>> newlist = new ArrayList<>();
        if(!hasselected)
        {
            String academic= studentService.findAcademic(userid);
            List<Topic> list=topicService.findAllTopicByAcademic(academic);
            List<Topic> availablelist=new ArrayList<>();
            for(Topic p:list)
            {
                if(p.getState()==1)
                    availablelist.add(p);
            }
            int length=availablelist.size();

            for(int i=0;i<length;i++){
                Teacher teacher =teacherService.findTeacher(availablelist.get(i).getUserid());
                Map<String,Object> map = new HashMap<>();
                map.put("topicid",availablelist.get(i).getTopicid());
                map.put("topic",availablelist.get(i).getTopic());
                map.put("userid",availablelist.get(i).getUserid());
                map.put("status",0);
                map.put("state",availablelist.get(i).getState());
                map.put("name",teacher.getName());
                map.put("description",availablelist.get(i).getDescription());
                newlist.add(map);
            }
            return newlist;
        }
        else
        {

            SelectTopic selectTopic = selectTopicService.findSelectTopicByStudentId(userid);
            Teacher teacher = teacherService.findTeacher(selectTopic.getTeacherid());
            Topic topic = topicService.findTopicById(selectTopic.getTopicid());
            Map<String,Object> map = new HashMap<>();
            map.put("topic",topic.getTopic());
            map.put("topicid",topic.getTopicid());
            map.put("description",topic.getDescription());
            map.put("academic",teacher.getAcademic());
            map.put("name",teacher.getName());
            map.put("status",1);
            newlist.add(map);
            return newlist;
        }


    }

    @RequestMapping("/showAcademicTopicManager")
    public List<Map<String,Object>> showAcademicTopicManager(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        String userid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                userid = cookie.getValue();
            }
        }
        String academic= managerService.findAcademic(userid);
        List<Topic> list=topicService.findAllTopicByAcademic(academic);
        int length=list.size();
        List<Map<String,Object>> newlist = new ArrayList<>();
        for(int i=0;i<length;i++){
            Teacher teacher=teacherService.findTeacher(list.get(i).getUserid());
            Map<String,Object> map = new HashMap<>();
            map.put("topicid",list.get(i).getTopicid());
            map.put("topic",list.get(i).getTopic());
            map.put("userid",list.get(i).getUserid());
            map.put("state",list.get(i).getState());
            map.put("description",list.get(i).getDescription());
            map.put("name",teacher.getName());
            newlist.add(map);
        }
        return newlist;

    }


//超管可以看见全部的题目并且审核
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

//学生只能看见通过了审核的题目进行选取
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

//学生只能选取对应学院的课程
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

    @RequestMapping("/showStudentBySingleTeacher")
    public List showStudentBySingleTeacher(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String teacherid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                teacherid = cookie.getValue();
            }
        }

        List<SelectTopic> list = selectTopicService.findSelectTopicByTeacher(teacherid);
        int length = list.size();
        List<Map<String,Object>> newlist = new ArrayList<>();
        for(int i=0;i<length;i++){
            SelectTopic selectTopic = list.get(i);
            Map<String,Object> map = new HashMap<>();
            Topic topic = topicService.findTopicById(selectTopic.getTopicid());
            Student student = studentService.findStudent(selectTopic.getStudentid());
            map.put("name",student.getName());
            map.put("studentid",selectTopic.getStudentid());
            map.put("title",topic.getTopic());
            map.put("topicid",selectTopic.getTopicid());
            map.put("record",recordService.findRecord(selectTopic.getStudentid(),selectTopic.getTeacherid()));
            int status = paperstateService.hasUploaded(selectTopic.getStudentid());
            map.put("hasUploaded",status);
            newlist.add(map);
        }
        return newlist;
    }

    @RequestMapping("/addRecord")
    public void addRecord(@RequestBody Map<String,String> map,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String teacherid = "";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("userid")){
                teacherid = cookie.getValue();
            }
        }
        String studentid = map.get("studentid");
        String content = map.get("content");
        recordService.addRecord(studentid,teacherid,content);
    }
}
