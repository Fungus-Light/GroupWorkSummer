package com.minqing.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topicgroup {
    @Id
    private String userid;
    private String groupid;
    private Integer identity;//教师=0，学生=1

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }
}
