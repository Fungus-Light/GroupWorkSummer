package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Topicgrouptch {
    @Id
    private String teacherid;
    private String groupid;

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
