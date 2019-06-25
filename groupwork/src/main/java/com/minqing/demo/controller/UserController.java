package com.minqing.demo.controller;

import com.minqing.demo.entity.Manager;
import com.minqing.demo.service.ManagerService;
import com.minqing.demo.service.StudentService;
import com.minqing.demo.service.TeacherService;
import com.minqing.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/login")
    public int login(@RequestBody Map<String,String> map, HttpServletResponse response){
        String userid = map.get("userid");
        String password = map.get("password");
        int number = userService.login(userid,password);
        if(number >= 3){
            response.addCookie(new Cookie("userid",userid));
        }
        return number;
    }
/*
    @RequestMapping("/refuseUser")
    public void refuseUser(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        userService.deleteUser(userid);
    }
*/

    @PostMapping("/addManager")
    public void addManager(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        String password = map.get("password");
        String name = map.get("name");
        String tel = map.get("tel");
        userService.addUser(userid,password,1);
        managerService.addManager(userid,name,tel);
    }

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

    @RequestMapping("/editManager")
    public void editManager(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        String password = map.get("password");
        String name = map.get("name");
        String tel = map.get("tel");
        userService.addUser(userid,password,1);
        managerService.addManager(userid,name,tel);
    }

    @RequestMapping("/deleteManager")
    public void deleteManager(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        managerService.deleteManager(userid);
        userService.deleteUser(userid);
    }

    @RequestMapping("/addStudent")
    public void addStudent(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        String password = map.get("password");
        String name = map.get("name");
        String tel = map.get("tel");
        String academic = map.get("academic");
        userService.addUser(userid,password,3);
        studentService.addStudent(userid,name,tel,academic);
    }

    @RequestMapping("/editStudent")
    public void editStudent(@RequestBody Map<String,String> map){
        String userid = map.get("userid");
        String password = map.get("password");
        String name = map.get("name");
        String tel = map.get("tel");
        String academic = map.get("academic");
        userService.addUser(userid,password,3);
        studentService.addStudent(userid,name,tel,academic);
    }

    @RequestMapping("/showStudent")
    public List
}
