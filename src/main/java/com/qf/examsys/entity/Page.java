package com.qf.examsys.entity;

import java.io.Serializable;

public class Page implements Serializable {

    private Integer pid;
    private String pTitle;
    private Integer sid;
    private String choose;
    private String judge;
    private String brief;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pid=" + pid +
                ", pTitle='" + pTitle + '\'' +
                ", sid=" + sid +
                ", choose='" + choose + '\'' +
                ", judge='" + judge + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
