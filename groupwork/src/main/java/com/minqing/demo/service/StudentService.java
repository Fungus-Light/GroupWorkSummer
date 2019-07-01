package com.minqing.demo.service;

import com.minqing.demo.entity.Student;
import com.minqing.demo.entity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Student> findStudents(){
        return studentRepository.findAll();
    }

    public Student findStudent(String userid){
        return studentRepository.findById(userid).get();
    }

    public String findAcademic(String studentid)
    {
        return studentRepository.findAcademicByid(studentid);
    }
    public List<String> findIdByAcademic(String academic){return studentRepository.findIdByAcademic(academic);}
//    public void setHasTopic(String studentid){studentRepository.SetStudentHastopic(studentid);}
}
