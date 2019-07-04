package com.minqing.demo.service;

import com.minqing.demo.entity.Teacher;
import com.minqing.demo.entity.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    public Teacher findTeacher(String userid){
        return teacherRepository.findById(userid).get();
    }

    public void deleteTeacher(String userid){
        teacherRepository.deleteById(userid);
    }

    public void addTeacher(String userid,String name,String tel,String academic){
        Teacher teacher = new Teacher();
        teacher.setUserid(userid);
        teacher.setName(name);
        teacher.setTel(tel);
        teacher.setAcademic(academic);
        teacherRepository.save(teacher);
    }
    public List<String> findIdByAcademic(String academic){return teacherRepository.findIdByAcademic(academic);}

    public String findTeacherNameByTeacherId(String teacherid){return teacherRepository.findNameByTeacherId(teacherid);}
}
