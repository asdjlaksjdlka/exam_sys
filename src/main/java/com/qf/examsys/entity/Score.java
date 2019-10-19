package com.qf.examsys.entity;

// 成绩
public class Score {

    private Integer scId;

    // 用户
    private User user;
    // 科目
    private Subject subject;
    // 考试
    private Exam exam;
    // 选择题成绩
    private Integer chooseScore;
    // 判断题成绩
    private Integer judgeScore;
    // 简答题成绩
    private Integer briefScore;
    // 总成绩
    private Integer totalScore;

    @Override
    public String toString() {
        return "Score{" +
                "scId=" + scId +
                ", user=" + user +
                ", subject=" + subject +
                ", exam=" + exam +
                ", chooseScore=" + chooseScore +
                ", judgeScore=" + judgeScore +
                ", briefScore=" + briefScore +
                ", totalScore=" + totalScore +
                '}';
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Integer getChooseScore() {
        return chooseScore;
    }

    public void setChooseScore(Integer chooseScore) {
        this.chooseScore = chooseScore;
    }

    public Integer getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(Integer judgeScore) {
        this.judgeScore = judgeScore;
    }

    public Integer getBriefScore() {
        return briefScore;
    }

    public void setBriefScore(Integer briefScore) {
        this.briefScore = briefScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
