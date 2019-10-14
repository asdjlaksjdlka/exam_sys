package com.qf.examsys.entity;

import java.io.Serializable;

public class Subject implements Serializable {
    private Integer sid;
    private String sName;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "sid=" + sid +
                ", sName='" + sName + '\'' +
                '}';
    }
}
