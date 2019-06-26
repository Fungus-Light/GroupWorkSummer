package com.example.demo.controller;

import com.example.demo.entity.user;
import com.example.demo.entity.userRepository;
import com.example.demo.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/login1")
public class usercontroller {
    @Autowired
    private userservice us;

    @RequestMapping("/save")
    public String login( )
    {
        return "j";
    }
}
