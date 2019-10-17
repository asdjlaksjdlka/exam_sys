package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examsys.dao.JudgeQuestionDao;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JudgeQuestionServiceImpl implements JudgeQuestionService {

    @Autowired
    private JudgeQuestionDao judgeQuestionDao;

    @Override
    public List<Judge> findAllJudge(Map<String, Integer> pageMap, String jTitle, Integer sid) {
        PageHelper.startPage(pageMap.get("page"),pageMap.get("limit"));
        List<Judge> allJudge = judgeQuestionDao.findAllJudge(jTitle, sid);
        return allJudge;
    }
}
