package com.qf.examsys.service.impl;

import com.qf.examsys.dao.ApplyDao;
import com.qf.examsys.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplyServiceImpl implements ApplyService {
    @Autowired
    ApplyService applyService;
    @Override
    public int applyInfo(Integer uid, Integer eid, Integer sid) {
        return applyService.applyInfo(uid,eid,sid);
    }
}
