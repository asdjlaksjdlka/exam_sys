package com.qf.examsys.service.impl;

import com.qf.examsys.dao.StatisticsDao;
import com.qf.examsys.entity.Apply;
import com.qf.examsys.entity.Score;
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
    public List<Score> listPersonalScore(Integer eId,Integer sid, Integer uid) {
        List<Score> scores = statisticsDao.listPersonalScore(eId,sid,uid);
        return scores;
    }

    @Override
    public List<Apply> listApply(Integer uid, Integer eId, Integer sid, Date time) {
        System.out.println(time);
        List<Apply> applies = statisticsDao.listApply(uid, eId, sid, time);
        return applies;
    }
}
