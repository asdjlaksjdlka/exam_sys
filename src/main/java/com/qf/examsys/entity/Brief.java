package com.qf.examsys.entity;

import java.io.Serializable;

public class Brief implements Serializable {

    private Integer bid;
    private String bTitle;
    private String bAnswer;
    private Integer sid;
    private Integer bScore;

    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getbScore() {
        return bScore;
    }

    public void setbScore(Integer bScore) {
        this.bScore = bScore;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getbTitle() {
        return bTitle;
    }

    public void setbTitle(String bTitle) {
        this.bTitle = bTitle;
    }

    public String getbAnswer() {
        return bAnswer;
    }

    public void setbAnswer(String bAnswer) {
        this.bAnswer = bAnswer;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Brief{" +
                "bid=" + bid +
                ", bTitle='" + bTitle + '\'' +
                ", bAnswer='" + bAnswer + '\'' +
                ", sid=" + sid +
                '}';
    }
}
