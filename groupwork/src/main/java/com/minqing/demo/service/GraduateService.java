package com.minqing.demo.service;


import com.minqing.demo.entity.Graduate;
import com.minqing.demo.entity.GraduateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduateService {
    @Autowired
    private GraduateRepository graduateRepository;
    public void sendApply(String studentid,String studentname,String finishtime,String topicname,
    String teachername,String func)
    {
        Graduate graduate=new Graduate();
        graduate.setFinishtime(finishtime);
        graduate.setFunc(func);
        graduate.setStudentid(studentid);
        graduate.setTeacherpass(0);
        graduate.setManagerpass(0);
        graduate.setTopicname(topicname);
        graduate.setTeachername(teachername);
        graduate.setStudentname(studentname);
        graduateRepository.save(graduate);
    }
    public int hasApplied(String studentid)
    {
        return graduateRepository.findById(studentid).isPresent()?1:0;
    }
    public int whoAgreed(String studentid)
    {
        return graduateRepository.findManagerpassById(studentid)&graduateRepository.findTeacherpassById(studentid);
    }

    public Graduate findById(String studentid)
    {
        return graduateRepository.findById(studentid).get();
    }

    public void TeacherAgreed(String studentid)
    {
        graduateRepository.TeacherAgreed(studentid);
    }

    public void ManagerAgreed(String studentid)
    {
        graduateRepository.ManagerAgreed(studentid);
    }
    public List findStudentidsByTeacherName(String teachername ){
        return graduateRepository.findStudentidsByTeacherName(teachername);
    }

    public List findStudentidsByManagerAcademic(String academic)
    {
        return graduateRepository.findStudentidsByManagerAcademic(academic);
    }
}
