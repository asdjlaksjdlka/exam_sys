package com.qf.examsys.service;

import com.qf.examsys.entity.Exam;

import java.util.HashMap;
import java.util.List;

public interface GetExamPaperService {

    /**
     * 选择考试
     */

    public List<Exam> selectAllExam();
    public Exam findSidByEid(Integer eid);
    /**
     * 生成试卷
     */
    public HashMap<String, List> makePaper(Integer uid, Integer eid, Integer sid);

    /**
     * 获取试卷
     * @param uid
     * @param eid
     * @param sid
     * @return
     */
    public HashMap<String, List> getPaper(Integer uid, Integer eid, Integer sid);

    /**
     * 无用
     */
    public Integer savaExamOneAnswer(Integer uid, Integer eid, String oneAnswer);

    /**
     * 交卷
     * @param uid
     * @param eid
     * @param TestAnswer
     * @param shortAnswer
     * @return
     */
    public Integer saveExamAnswer(Integer uid,Integer eid,String TestAnswer,String shortAnswer);

}
