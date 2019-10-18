package com.qf.examsys.service;


import com.qf.examsys.entity.Record;

import java.util.HashMap;
import java.util.List;

public interface GetExamPaperService {

    /**
     * 插入数据库
     */

    public HashMap<String, List> makePaper(Integer uid, Integer eid, Integer sid);
}
