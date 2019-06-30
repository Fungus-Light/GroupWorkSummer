package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Period {
    @Id
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

/*
    status值：
    0代表未开始
    1代表开题
    2代表开题答辩
    3代表毕业答辩
    4代表结束
 */

}
