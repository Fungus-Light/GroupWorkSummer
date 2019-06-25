package com.minqing.demo.service;

import com.minqing.demo.entity.User;
import com.minqing.demo.entity.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public int login(String userid,String password){
        Optional optional = userRepository.findById(userid);
        //账号不存在的情况返回0
        if(!optional.isPresent())   return 0;
        User user = (User)optional.get();
        //账号未被激活的情况返回1
        if(user.getStatus() == 0)   return 1;
        //密码错误的情况返回2
        if(!user.getPassword().equals(password))    return 2;
        int temp = user.getTitle();
        //返回值为3进入超管页面
        if(temp == 0)    return 3;
        //返回值为4进入管理员页面
        else if(temp == 1)   return 4;
        //返回值为5进入教师页面
        else if(temp == 2)   return 5;
        //返回值为6进入学生页面
        else    return 6;
    }

    public void addUser(String userid,String password,int title){
        User user = new User();
        user.setUserid(userid);
        user.setPassword(password);
        user.setStatus(1);
        user.setTitle(title);
        userRepository.save(user);
    }



    public void deleteUser(String userid){
        User user = userRepository.findById(userid).get();
        user.setStatus(0);
        userRepository.save(user);
    }


}
