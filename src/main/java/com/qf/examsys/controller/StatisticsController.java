package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.*;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping(path = "/score")
    @ResponseBody
    public JsonReasult scoreStatistics(Exam exam, Subject subject, User user){
        try {
            System.out.println(user);
            List<Score> scores = statisticsService.listPersonalScore(exam.geteId(), subject.getSid(), user.getUid());
            System.out.println(scores);
            return new JsonReasult(0,scores);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonReasult(1,"查询失败");
        }
    }

    @PostMapping(path = "/apply")
    @ResponseBody
    public JsonReasult applyStatistics(Integer uId, Integer eId, Integer sid, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date time){
        List<Apply> applies = statisticsService.listApply(uId, eId, sid, time);
        System.out.println(applies);
        return new JsonReasult(0,applies);
    }
}
