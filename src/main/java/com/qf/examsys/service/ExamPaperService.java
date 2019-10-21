package com.qf.examsys.service;

import com.qf.examsys.entity.Record;

import java.util.List;

public interface ExamPaperService {
    List<Record> findExamPaperByUid(Integer uid);
}
