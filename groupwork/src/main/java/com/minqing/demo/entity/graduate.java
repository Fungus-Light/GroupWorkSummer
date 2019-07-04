package com.minqing.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class graduate {
    @Id
    private String studentid;
    private String studentname;
    private String finishtime;
    private String topicname;
    private String teachername;
    private String func;//添加自己项目功能概要
    private Integer teacherpass;
    private Integer managerpass;

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public Integer getTeacherpass() {
        return teacherpass;
    }

    public void setTeacherpass(Integer teacherpass) {
        this.teacherpass = teacherpass;
    }

    public Integer getManagerpass() {
        return managerpass;
    }

    public void setManagerpass(Integer managerpass) {
        this.managerpass = managerpass;
    }
}
