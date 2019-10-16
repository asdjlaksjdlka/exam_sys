package com.qf.examsys.dao;

import com.qf.examsys.entity.Record;
import com.qf.examsys.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatisticsDao {

    // 学生单人成绩统计
    List<Score> listPersonalScore(@Param(value = "eid") Integer eid, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);

    // 所有学生成绩统计
    Map<Integer, List<Score>> listAllStudentsScore();

    // 单次考试学生成绩统计
    List<Score> listOnceExamScore();

    // 学生单人考试记录统计
    List<Record> listPersonalRecord();

    // 所有学生考试记录统计
    Map<Integer, List<Record>> listAllStudentsRecord();

}
