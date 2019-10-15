package com.qf.examsys.dao;

import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {

    public List<Subject> findAllSubject();

    public List<Choose> findAllChoose(@Param("cTitle") String cTitle, @Param("sid") Integer sid);

    public Choose findQuestionById(Integer cid);

    public Integer updateQuestionById(Choose choose);
}
