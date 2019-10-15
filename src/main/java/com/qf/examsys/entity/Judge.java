package com.qf.examsys.entity;

import java.io.Serializable;

public class Judge implements Serializable {

    private Integer jid;
    private String jTitle;
    private String jAnswer;
    private Integer sid;
    private Integer jScore;

    public Integer getjScore() {
        return jScore;
    }

    public void setjScore(Integer jScore) {
        this.jScore = jScore;
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getjTitle() {
        return jTitle;
    }

    public void setjTitle(String jTitle) {
        this.jTitle = jTitle;
    }

    public String getjAnswer() {
        return jAnswer;
    }

    public void setjAnswer(String jAnswer) {
        this.jAnswer = jAnswer;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Judge{" +
                "jid=" + jid +
                ", jTitle='" + jTitle + '\'' +
                ", jAnswer='" + jAnswer + '\'' +
                ", sid=" + sid +
                '}';
    }
}
