package com.qf.examsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    private Integer eid;
    private String eName;
    private Date beginTime;
    private Date endTime;
    private Integer pid;
    private Integer sid;
    private Subject subject;

    @Override
    public String toString() {
        return "Exam{" +
                "eid=" + eid +
                ", eName='" + eName + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", pid=" + pid +
                ", sid=" + sid +
                ", subject=" + subject +
                '}';
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getBeginTime() {
        return beginTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getEndTime() {
        return endTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
