package com.qf.examsys.service;

import com.qf.examsys.entity.*;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<Score> listPersonalScore(Integer eid, Integer sid, Integer uid, Integer page, Integer limit);

    List<Apply> listApply(Integer uid, Integer eid, Integer sid, Date time);
    // 查询有哪些科目
    List<Subject> listSubject(Integer sid);

    // 查询有哪些考试
    List<Exam> listExam(Integer eid);

    // 查询有哪些用户
    List<User> listUser(Integer uid);

    // 按考试进行考试人数统计
    List<ExamNumberStatistics> listExamNumber();

    // 按科目进行考试人数统计
    List<ExamNumberStatistics> listExamSubjectNumber();

    // 成绩统计
    List<ScoreStatistics> listScore(Integer eid, Integer sid, Integer uid);

}
