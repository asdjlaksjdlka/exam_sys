package com.qf.examsys.entity;

// 成绩统计
public class ScoreStatistics {
    // 考试总人数
    private Integer countNumber;
    // 及格人数
    private Integer passNumber;
    // 及格率
    private Double passRate;

    // 总成绩最大值、最小值、平均值
    private Integer maxTotalScore;
    private Integer minTotalScore;
    private Integer avgTotalScore;

    // 选择题成绩最大值、最小值、平均值
    private Integer maxChooseScore;
    private Integer minChooseScore;
    private Integer avgChooseScore;

    // 判断题成绩最大值、最小值、平均值
    private Integer maxJudgeScore;
    private Integer minJudgeScore;
    private Integer avgJudgeScore;

    // 简答题成绩最大值、最小值、平均值
    private Integer maxBriefScore;
    private Integer minBriefScore;
    private Integer avgBriefScore;

    @Override
    public String toString() {
        return "ScoreStatistics{" +
                "countNumber=" + countNumber +
                ", passNumber=" + passNumber +
                ", passRate=" + passRate +
                ", maxTotalScore=" + maxTotalScore +
                ", minTotalScore=" + minTotalScore +
                ", avgTotalScore=" + avgTotalScore +
                ", maxChooseScore=" + maxChooseScore +
                ", minChooseScore=" + minChooseScore +
                ", avgChooseScore=" + avgChooseScore +
                ", maxJudgeScore=" + maxJudgeScore +
                ", minJudgeScore=" + minJudgeScore +
                ", avgJudgeScore=" + avgJudgeScore +
                ", maxBriefScore=" + maxBriefScore +
                ", minBriefScore=" + minBriefScore +
                ", avgBriefScore=" + avgBriefScore +
                '}';
    }

    public Integer getCountNumber() {
        return countNumber;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Integer getMaxTotalScore() {
        return maxTotalScore;
    }

    public void setMaxTotalScore(Integer maxTotalScore) {
        this.maxTotalScore = maxTotalScore;
    }

    public Integer getMinTotalScore() {
        return minTotalScore;
    }

    public void setMinTotalScore(Integer minTotalScore) {
        this.minTotalScore = minTotalScore;
    }

    public Integer getAvgTotalScore() {
        return avgTotalScore;
    }

    public void setAvgTotalScore(Integer avgTotalScore) {
        this.avgTotalScore = avgTotalScore;
    }

    public Integer getMaxChooseScore() {
        return maxChooseScore;
    }

    public void setMaxChooseScore(Integer maxChooseScore) {
        this.maxChooseScore = maxChooseScore;
    }

    public Integer getMinChooseScore() {
        return minChooseScore;
    }

    public void setMinChooseScore(Integer minChooseScore) {
        this.minChooseScore = minChooseScore;
    }

    public Integer getAvgChooseScore() {
        return avgChooseScore;
    }

    public void setAvgChooseScore(Integer avgChooseScore) {
        this.avgChooseScore = avgChooseScore;
    }

    public Integer getMaxJudgeScore() {
        return maxJudgeScore;
    }

    public void setMaxJudgeScore(Integer maxJudgeScore) {
        this.maxJudgeScore = maxJudgeScore;
    }

    public Integer getMinJudgeScore() {
        return minJudgeScore;
    }

    public void setMinJudgeScore(Integer minJudgeScore) {
        this.minJudgeScore = minJudgeScore;
    }

    public Integer getAvgJudgeScore() {
        return avgJudgeScore;
    }

    public void setAvgJudgeScore(Integer avgJudgeScore) {
        this.avgJudgeScore = avgJudgeScore;
    }

    public Integer getMaxBriefScore() {
        return maxBriefScore;
    }

    public void setMaxBriefScore(Integer maxBriefScore) {
        this.maxBriefScore = maxBriefScore;
    }

    public Integer getMinBriefScore() {
        return minBriefScore;
    }

    public void setMinBriefScore(Integer minBriefScore) {
        this.minBriefScore = minBriefScore;
    }

    public Integer getAvgBriefScore() {
        return avgBriefScore;
    }

    public void setAvgBriefScore(Integer avgBriefScore) {
        this.avgBriefScore = avgBriefScore;
    }
}
