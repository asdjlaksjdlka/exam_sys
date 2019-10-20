package com.qf.examsys.dao;

import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Judge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JudgeQuestionDao {

    public List<Judge> findAllJudge(@Param("jTitle") String jTitle, @Param("sid") Integer sid);

    //修改回显
    public Judge findJudgeQuestionById(Integer jid);


    //提交修改
    public Integer updateJudgeById(Judge judge);

    /**
     * 添加判断题
     */
    public Integer addJudge(Judge judge);

    //删除题目
    public Integer deleteOneById(Judge judge);
    //批量删除
    public Integer deletAlljudge(Integer[] ids);


    /*
     * 导入导出
     * */

    public int addJudges(@Param("judges") List<Judge> judges);

}
