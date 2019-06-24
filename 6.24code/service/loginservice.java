//package com.example.demo.service;
//
//
//
//import com.example.demo.entity.userRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Service;
//import  com.example.demo.entity.user;
//import java.util.List;
//import java.util.Optional;
//
///**
// * Created by huangds on 2017/10/28.
// */
//@Service
//public class loginservice {
//
//    @Autowired
//    private userRepository ur;
//
//    public boolean verifyLogin(user user){
//
//       Optional op = ur.findById(user.getUserid());
//
//        if(op.isPresent())
//        {
//          user u=(user)op.get();
//          if(u.getPassword().equals(user.getPassword()))
//              return true;
//          else
//              return  false;
//        }
//        else
//            return  false;
//    }
//
//}
