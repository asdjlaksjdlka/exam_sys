package com.qf.examsys.entity;

import java.io.Serializable;

public class Record implements Serializable {

    private Integer reid;
    private Integer uid;
    private String title;
    private String uAnswer;
    private String rAnswer;
    private Integer eid;

    public Integer getReid() {
        return reid;
    }

    public void setReid(Integer reid) {
        this.reid = reid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getuAnswer() {
        return uAnswer;
    }

    public void setuAnswer(String uAnswer) {
        this.uAnswer = uAnswer;
    }

    public String getrAnswer() {
        return rAnswer;
    }

    public void setrAnswer(String rAnswer) {
        this.rAnswer = rAnswer;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Record{" +
                "reid=" + reid +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", uAnswer='" + uAnswer + '\'' +
                ", rAnswer='" + rAnswer + '\'' +
                ", eid=" + eid +
                '}';
    }
}
