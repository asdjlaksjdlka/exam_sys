package com.qf.examsys.service;

import com.qf.examsys.entity.Score;

import java.util.List;

public interface StatisticsService {

    List<Score> listPersonalScore(Integer eId,Integer sid, Integer uid);
}
