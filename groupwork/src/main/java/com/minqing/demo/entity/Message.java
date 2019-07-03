package com.minqing.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    private int messageid;
    private String title;
    private String content;
    private String time;

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
