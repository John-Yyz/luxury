package com.luxury.model;

import java.util.Date;

public class User_info {
    private String  id;
    private String uname;
    private Date create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return uname;
    }

    public void setName(String name) {
        this.uname = name;
    }

    public Date getCreate_time() {
        return create_time;

    }

    public void setCreate_time(Date name) {
        this.create_time = name;
    }
}
