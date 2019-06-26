package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topicgroupforstudent {

    private Integer groupid;
    @Id
    private String studentid;

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }



    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }
}
