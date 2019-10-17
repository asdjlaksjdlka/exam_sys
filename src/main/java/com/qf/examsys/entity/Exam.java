package com.qf.examsys.entity;

import java.util.Date;

// 考试
public class Exam {

    private  Integer eId;

    // 考试名
    private String name;

    // 开始时间
    private Date beginTime;

    // 结束时间
    private Date endTime;

    // 试卷id
    private Integer pid;

    @Override
    public String toString() {
        return "Exam{" +
                "eId=" + eId +
                ", 考试名name='" + name + '\'' +
                ", 开始时间beginTime=" + beginTime +
                ", 结束时间endTime=" + endTime +
                ", 试卷pid=" + pid +
                '}';
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
