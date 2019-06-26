package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Period {
    @Id
    private int segid;

    public int getSegid() {
        return segid;
    }

    public void setSegid(int segid) {
        this.segid = segid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;

}
