package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examsys.dao.QuestionDao;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Subject> findAllSubject() {
        return questionDao.findAllSubject();
    }

    @Override
    public List<Choose> findAllChoose(Map<String, Integer> pageMap,String cTitle,Integer sid) {
        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
        System.out.println(questionDao.findAllChoose(cTitle,sid));

        PageHelper.startPage(pageMap.get("page"), pageMap.get("limit"));
        return questionDao.findAllChoose(cTitle,sid);
    }
}