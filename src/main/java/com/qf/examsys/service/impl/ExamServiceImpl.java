package com.qf.examsys.service.impl;

import com.qf.examsys.dao.ExamDao;
import com.qf.examsys.entity.Exam;
import com.qf.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;

    @Override
    public List<Exam> allExam() {
        return examDao.allExam();
    }

    @Override
    public int addTime(Exam exam) {

        return examDao.addTime(exam);
    }

    @Override
    public int delTimeById(Integer eId) {
        return examDao.delTimeById(eId);
    }

    @Override
    public Exam findExamById(Integer eId) {
        return examDao.findExamById(eId);
    }

    @Override
    public int updExam(Exam exam) {
        return examDao.updExam(exam);
    }
}
