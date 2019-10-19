package com.qf.examsys.entity;

// 考试人数统计
public class ExamNumberStatistics {

    // 考试id
    private Integer eid;
    // 考试名
    private String eName;
    // 人数
    private Integer count;
    // 科目
//    private Subject subject;

    private Integer sid;
    private String  sName;

    @Override
    public String toString() {
        return "ExamNumberStatistics{" +
                "eid=" + eid +
                ", eName='" + eName + '\'' +
                ", count=" + count +
                ", sid=" + sid +
                ", sName='" + sName + '\'' +
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
