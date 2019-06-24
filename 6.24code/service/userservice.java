package com.example.demo.service;

import com.example.demo.entity.user;
import com.example.demo.entity.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class userservice {
    @Autowired
    private userRepository ur;



}
