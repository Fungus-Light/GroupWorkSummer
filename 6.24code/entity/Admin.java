package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Admin {
    @Id
    private String userid;
    private String name;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    private String Tel;
}
