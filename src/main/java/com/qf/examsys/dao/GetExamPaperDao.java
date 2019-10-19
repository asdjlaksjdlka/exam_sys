package com.qf.examsys.dao;

import com.qf.examsys.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GetExamPaperDao {


    /**
     * 选择考试
     */
    public List<Exam> selectAllExam();

    public Exam findSidByEid(Integer eid);
    /**
     * 随机生成选择题
     * @return
     */
    public List<Choose> getChooseByRand(Integer sid);
    /**
     * 随机生成判断题
     * @return
     */
    public List<Judge> getJudgeByRand(Integer sid);
    /**
     * 随机生成简答题
     */
    public List<Brief> getBriefByRand(Integer sid);


    /**
     * 插入数据库
     */

    public Integer makePaper();

    /**
     * 单选题提交数据库
     */

    public Integer addChooseExam(Integer uid, Integer eid,String title,Integer sid,String rAnswer);


    /**
     * 交卷
     * @param list
     * @return
     */
    public Integer commitExam(@Param("recordList") List<Record> list);
}
