package com.qf.examsys.service.impl;

import com.qf.examsys.dao.StatisticsDao;
import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired(required = false)
    private StatisticsDao statisticsDao;
    @Override
    public List<Score> listPersonalScore(User user) {
        List<Score> scores = statisticsDao.listPersonalScore(1,1,1);
        return scores;
    }
}
