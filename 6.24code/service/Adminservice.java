package com.example.demo.service;

import com.example.demo.entity.AdminRepository;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherRepository;
import com.example.demo.entity.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Adminservice {
    @Autowired
    private TeacherRepository tr;
    @Autowired
    private userRepository ur;

   public List<Teacher> findAllTeacher()
    {
        return tr.findAll();
    }
    public Teacher findTeacherById(String id){return (Teacher)tr.findById(id).get();}
    public int UpdateTeacherInf(Teacher newteacher)
    {
        if(tr.findById(newteacher.getUserid()).isPresent())
        {
            tr.save(newteacher);
            return 0;
        }
        else return 1;
    }
    public int InsertTeacherInf(Teacher t)
    {
        tr.save(t);
        return 0;
    }



}
