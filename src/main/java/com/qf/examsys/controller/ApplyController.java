package com.qf.examsys.controller;

import com.qf.examsys.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yqz
 */
public class ApplyController {
    @Autowired
    ApplyService applyService;

    @RequestMapping("/applyInfo")
    public Map<String,Object> AddApplyInfo(Integer uid, Integer eid, Integer sid){
        Map<String,Object> map = new HashMap<>();
        int i = applyService.applyInfo(uid, eid, sid);
        if (i > 0){
            map.put("code",1);
        }
        return map;

    }
}
