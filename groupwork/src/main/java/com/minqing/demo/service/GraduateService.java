package com.minqing.demo.service;


import com.minqing.demo.entity.Graduate;
import com.minqing.demo.entity.GraduateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
