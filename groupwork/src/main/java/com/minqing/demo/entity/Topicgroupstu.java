package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topicgroupstu {

    private String groupid;
    @Id
    private String studentid;


    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
