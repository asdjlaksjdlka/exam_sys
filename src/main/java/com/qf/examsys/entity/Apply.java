package com.qf.examsys.entity;

import java.util.Date;

public class Apply {
    private Integer applyId;
    // 用户
    private User user;
    // 考试
    private Exam exam;
    // 科目id
    private Subject subject;
    // 报名时间
    private Date time;

    public Apply() {
    }

    @Override
    public String toString() {
        return "Apply{" +
                "appiyId=" + applyId +
                ", user=" + user +
                ", exam=" + exam +
                ", subject=" + subject +
                ", time=" + time +
                '}';
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
