package com.qf.examsys.service;

import com.qf.examsys.entity.*;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<Score> listPersonalScore(Integer eId,Integer sid, Integer uid);

    List<Apply> listApply(Integer uid, Integer eId, Integer sid, Date time);

    List<Subject> listSubject();

    // 按考试进行考试人数统计
    List<ExamNumberStatistics> listExamNumber();

    // 按科目进行考试人数统计
    List<ExamNumberStatistics> listExamSubjectNumber();

    // 成绩统计
    List<ScoreStatistics> listScore(Integer eid,Integer sid, Integer uid);

}
