package com.qf.examsys.dao;

import com.qf.examsys.entity.Apply;
import com.qf.examsys.entity.Page;
import com.qf.examsys.entity.Record;
import com.qf.examsys.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatisticsDao {

    // 学生成绩统计
    List<Score> listPersonalScore(@Param(value = "eId") Integer eId, @Param(value = "sid")Integer sid, @Param(value = "uid")Integer uid);

    // 报名信息统计
    List<Apply> listApply(@Param(value = "uid")Integer uid, @Param(value = "eId") Integer eId, @Param(value = "sid")Integer sid, @Param(value = "time")Date time);

    // 试卷信息统计
    List<Page> listPage();

    // 学生考试记录统计
    List<Record> listPersonalRecord();

}
