package com.qf.examsys.service;

import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.User;

import java.util.List;

public interface StatisticsService {

    List<Score> listPersonalScore(User user);
}
