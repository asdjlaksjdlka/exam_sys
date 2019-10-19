package com.qf.examsys.dao;

import com.qf.examsys.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatisticsDao {

    // 学生成绩
    List<Score> listPersonalScore(@Param(value = "eId") Integer eId, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);

    // 报名信息
    List<Apply> listApply(@Param(value = "uid")Integer uid, @Param(value = "eId") Integer eId, @Param(value = "sid")Integer sid, @Param(value = "time")Date time);

    // 按考试进行考试人数统计
    List<ExamNumberStatistics> listExamNumber();

    // 按科目进行考试人数统计
    List<ExamNumberStatistics> listExamSubjectNumber();

    // 成绩统计
    List<ScoreStatistics> listScore(@Param(value = "eid") Integer eid, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);

    // 查询有哪些科目
    List<Subject> listSubject();

}
