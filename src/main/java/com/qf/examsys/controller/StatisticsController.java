package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Exam;
import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
