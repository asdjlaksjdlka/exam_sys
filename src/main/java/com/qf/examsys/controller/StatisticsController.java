package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.*;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @PostMapping(path = "/score")
    public JsonReasult listScore(Integer eId, Integer sid, Integer uid){
        try {
            List<Score> scores = statisticsService.listPersonalScore(eId, sid, uid);
            System.out.println(scores);
            return new JsonReasult(0,scores);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonReasult(1,"查询失败");
        }
    }

    @ResponseBody
    @PostMapping(path = "/score/json",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonReasult scoreStatisticsJson(Integer eId,Integer sid, Integer uid){
        try {
            List<Score> scores = statisticsService.listPersonalScore(eId, sid, uid);
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

    @PostMapping(path = "/subject")
    @ResponseBody
    public JsonReasult listSubject(){
        List<Subject> subjects = statisticsService.listSubject();
        return new JsonReasult(0,subjects);
    }

    // 按考试进行考试人数统计
    @PostMapping(path = "/examNumber")
    @ResponseBody
    public JsonReasult listExamNumber(){
        List<ExamNumberStatistics> examNumber = statisticsService.listExamNumber();
        return new JsonReasult(0, examNumber, "考试报名人数统计", null);
    }

    // 按科目进行考试人数统计
    @PostMapping(path = "/examSubjectNumber")
    @ResponseBody
    public JsonReasult listExamSubjectNumber(){
        List<ExamNumberStatistics> examSubjectNumber = statisticsService.listExamSubjectNumber();
        return new JsonReasult(0,examSubjectNumber, "各学科考试人数统计", null);
    }

    // 成绩统计
    @PostMapping(path = "/scoreStatistics")
    @ResponseBody
    public JsonReasult scoreStatistics(Integer eid, Integer sid, Integer uid){
        List<ScoreStatistics> scoreStatistics = statisticsService.listScore(eid, sid, uid);
        JsonReasult jsonReasult = new JsonReasult(0, scoreStatistics);
        if (eid != null){
            
        }
        if (sid != null){

        }
        if (uid != null){

        }
        return new JsonReasult(0,scoreStatistics);
    }
}

