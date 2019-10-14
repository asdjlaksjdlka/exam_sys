package com.qf.examsys.service.impl;

import com.qf.examsys.dao.QuestionDao;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Subject> findAllSubject() {
        return questionDao.findAllSubject();
    }
}
