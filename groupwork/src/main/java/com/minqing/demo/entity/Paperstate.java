package com.minqing.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Paperstate {
    @Id
    private String studentid;
    private Integer state;
    //state=0待审核 =1通过 =2拒绝

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
