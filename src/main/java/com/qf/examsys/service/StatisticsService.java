package com.qf.examsys.service;

import com.qf.examsys.entity.Apply;
import com.qf.examsys.entity.Score;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<Score> listPersonalScore(Integer eId,Integer sid, Integer uid);

    List<Apply> listApply(Integer uid, Integer eId, Integer sid, Date time);
}
