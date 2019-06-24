package com.minqing.demo.service;

import com.minqing.demo.entity.Student;
import com.minqing.demo.entity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(String userid,String name,String tel,String academic){
        Student student = new Student();
        student.setUserid(userid);
        student.setName(name);
        student.setTel(tel);
        student.setAcademic(academic);
        studentRepository.save(student);
    }

    public void deleteStudent(String userid){
        studentRepository.deleteById(userid);
    }

    public void updateStudent(String userid,String name,String tel,String academic){
        Student student = studentRepository.findById(userid).get();
        student.setName(name);
        student.setTel(tel);
        student.setAcademic(academic);
        studentRepository.save(student);
    }

}
