package com.qf.examsys.dao;

import com.qf.examsys.entity.Judge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JudgeQuestionDao {

    public List<Judge> findAllJudge(@Param("jTitle") String jTitle, @Param("sid") Integer sid);

}
