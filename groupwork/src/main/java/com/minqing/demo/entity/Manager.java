package com.minqing.demo.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Manager {
    @Id
    private String userid;
    private String name;
    private String tel;
    private String academic;

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

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
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
