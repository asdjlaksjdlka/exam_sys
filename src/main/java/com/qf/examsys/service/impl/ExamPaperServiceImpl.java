package com.qf.examsys.service.impl;

import com.qf.examsys.dao.ExamPaperDao;
import com.qf.examsys.entity.Record;
import com.qf.examsys.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExamPaperServiceImpl implements ExamPaperService {
    @Autowired
    ExamPaperDao examPaperDao;

    @Override
    public List<Record> findExamPaperByUid(Integer uid) {
        return examPaperDao.findExamPaperByUid(uid);
    }
}
