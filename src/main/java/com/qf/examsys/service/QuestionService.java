package com.qf.examsys.service;

import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;

import java.util.List;
import java.util.Map;

public interface QuestionService {

    public List<Subject> findAllSubject();

    public List<Choose> findAllChoose(Map<String, Integer> pageMap,String cTitle,Integer sid);
    //修改回显
    public Choose findQuestionById(Integer cid);
    //修改提交
    public Integer updateQuestionById(Choose choose);

}
