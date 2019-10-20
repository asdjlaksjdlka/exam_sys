package com.qf.examsys.dao;

import com.qf.examsys.entity.Exam;

import java.util.List;

public interface ExamDao {
    public List<Exam> allExam();
    public int addTime(Exam exam);
    public int delTimeById(Integer eId);
    public Exam findExamById(Integer eId);
    public int updExam(Exam exam);
}
