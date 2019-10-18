package com.qf.examsys.entity;


import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Record implements Serializable {

    private Integer reid;
    private Integer uid;
    private String title;
    private String uAnswer;
    private String rAnswer;
    private Integer eid;
    private Integer sid;
    private Integer rScore;

    public Integer getrScore() {
        return rScore;
    }

    public void setrScore(Integer rScore) {
        this.rScore = rScore;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

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
