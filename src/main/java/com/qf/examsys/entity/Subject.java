package com.qf.examsys.entity;


import java.io.Serializable;


public class Subject implements Serializable {
    private Integer sid;
    private String sName;

    public Subject() {
    }

    public Subject(String sName) {
        this.sName = sName;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject nation = (Subject) o;

        return sName != null ? sName.equals(nation.sName) : nation.sName == null;
    }

    @Override
    public int hashCode() {
        return sName != null ? sName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "sid=" + sid +
                ", sName='" + sName + '\'' +
                '}';
    }
}
