package com.qf.examsys.entity;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    private Integer eid;
    private String eName;
    private Date beginTime;
    private Date endTime;
    private Integer pid;
    private Integer sid;

    @Override
    public String toString() {
        return "Exam{" +
                "eid=" + eid +
                ", eName='" + eName + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", pid=" + pid +
                ", sid=" + sid +
                '}';
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
