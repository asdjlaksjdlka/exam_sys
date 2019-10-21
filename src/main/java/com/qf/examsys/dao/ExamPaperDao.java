package com.qf.examsys.dao;

import com.qf.examsys.entity.Record;

import java.util.List;

public interface ExamPaperDao {
    //根据用户id批量查询已经提交的试卷
    List<Record> findExamPaperByUid(Integer uid);
    //改卷

}
