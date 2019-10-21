package com.qf.examsys.dao;

public interface ApplyDao {
    //添加报名信息 根据学生id，考试id，科目id
    int applyInfo(Integer uid,Integer eid,Integer sid);
}
