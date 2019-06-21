package com.minqing.demo.service;

import com.minqing.demo.entity.User;
import com.minqing.demo.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if(user.getTitle() == 0)    return 3;
        else if(user.getTitle() == 1)   return 4;
        else    return 5;
    }
}
