package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examsys.dao.StatisticsDao;
import com.qf.examsys.entity.*;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;
    @Override
    public List<Score> listPersonalScore(Integer eid,Integer sid, Integer uid, Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Score> scores = statisticsDao.listPersonalScore(eid,sid,uid);
        return scores;
    }

    @Override
    public List<Apply> listApply(Integer uid, Integer eid, Integer sid, Date time) {
        List<Apply> applies = statisticsDao.listApply(uid, eid, sid, time);
        return applies;
    }

    @Override
    public List<Subject> listSubject(Integer sid) {
        List<Subject> subjects = statisticsDao.listSubject(sid);
        return subjects;
    }

    @Override
    public List<Exam> listExam(Integer eid) {
        return statisticsDao.listExam(eid);
    }

    @Override
    public List<User> listUser(Integer uid) {
        return statisticsDao.listUser(uid);
    }

    @Override
    public List<ExamNumberStatistics> listExamNumber() {
        List<ExamNumberStatistics> examNumber = statisticsDao.listExamNumber();
        return examNumber;
    }

    @Override
    public List<ExamNumberStatistics> listExamSubjectNumber() {
        List<ExamNumberStatistics> examSubjectNumber = statisticsDao.listExamSubjectNumber();
        return examSubjectNumber;
    }

    @Override
    public List<ScoreStatistics> listScore(Integer eid, Integer sid, Integer uid) {
        List<ScoreStatistics> scoreStatistics = statisticsDao.listScore(eid, sid, uid);
        return scoreStatistics;
    }
}
