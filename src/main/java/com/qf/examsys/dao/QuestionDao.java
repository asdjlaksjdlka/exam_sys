package com.qf.examsys.dao;

import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionDao {

    public List<Subject> findAllSubject();

    public List<Choose> findAllChoose(@Param("cTitle") String cTitle, @Param("sid") Integer sid);
    //修改回显
    public Choose findQuestionById(Integer cid);
    //修改提交
    public Integer updateQuestionById(Choose choose);
    //删除题目
    public Integer deleteOneById(Choose choose);

    //批量删除
    public Integer deletAlluQestion(Integer[] ids);

    /**
     * 添加选择题
     */
    public Integer addQuestion(Choose choose);
}
