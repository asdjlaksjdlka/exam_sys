package com.qf.examsys.dao;

import com.qf.examsys.entity.Subject;

import java.util.List;

public interface QuestionDao {

    public List<Subject> findAllSubject();
}
