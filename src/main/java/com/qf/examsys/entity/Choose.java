package com.qf.examsys.entity;

import java.io.Serializable;

public class Choose implements Serializable {

    private Integer cid;
    private String cTitle;
    private String cOptionA;
    private String cOptionB;
    private String cOptionC;
    private String cOptionD;
    private String cAnswer;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getcTitle() {
        return cTitle;
    }

    public void setcTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcOptionA() {
        return cOptionA;
    }

    public void setcOptionA(String cOptionA) {
        this.cOptionA = cOptionA;
    }

    public String getcOptionB() {
        return cOptionB;
    }

    public void setcOptionB(String cOptionB) {
        this.cOptionB = cOptionB;
    }

    public String getcOptionC() {
        return cOptionC;
    }

    public void setcOptionC(String cOptionC) {
        this.cOptionC = cOptionC;
    }

    public String getcOptionD() {
        return cOptionD;
    }

    public void setcOptionD(String cOptionD) {
        this.cOptionD = cOptionD;
    }

    public String getcAnswer() {
        return cAnswer;
    }

    public void setcAnswer(String cAnswer) {
        this.cAnswer = cAnswer;
    }

    @Override
    public String toString() {
        return "Choose{" +
                "cid=" + cid +
                ", cTitle='" + cTitle + '\'' +
                ", cOptionA='" + cOptionA + '\'' +
                ", cOptionB='" + cOptionB + '\'' +
                ", cOptionC='" + cOptionC + '\'' +
                ", cOptionD='" + cOptionD + '\'' +
                ", cAnswer='" + cAnswer + '\'' +
                '}';
    }
}
