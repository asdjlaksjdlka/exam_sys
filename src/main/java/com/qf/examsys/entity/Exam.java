package com.qf.examsys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Exam {
    private Integer eId;
    private String eName;
    private Date beginTime;
    private Date endTime;
    private Integer pId;

    public Exam() {
    }

    public Exam(Integer eId, String eName, Date beginTime, Date endTime, Integer pId) {
        this.eId = eId;
        this.eName = eName;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.pId = pId;
    }

    public Integer geteId() {
        return this.eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteName() {
        return this.eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getBeginTime() {
        return this.beginTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getEndTime() {
        return this.endTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getpId() {
        return this.pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
