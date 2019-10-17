package com.qf.examsys.service;

import com.qf.examsys.entity.Judge;

import java.util.List;
import java.util.Map;

public interface JudgeQuestionService {
    public List<Judge> findAllJudge(Map<String, Integer> pageMap, String jTitle, Integer sid);
}
