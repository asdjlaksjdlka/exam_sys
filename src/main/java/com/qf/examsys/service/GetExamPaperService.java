package com.qf.examsys.service;

import java.util.HashMap;
import java.util.List;

public interface GetExamPaperService {

    /**
     * 插入数据库
     */

    public HashMap<String, List> makePaper(Integer uid, Integer eid, Integer sid);
    public HashMap<String, List> getPaper(Integer uid, Integer eid, Integer sid);

    public Integer savaExamOneAnswer(Integer uid, Integer eid, String oneAnswer);


    public Integer saveExamAnswer(Integer uid,Integer eid,String TestAnswer,String shortAnswer);

}
