package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.*;
import com.qf.examsys.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path = "/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ResponseBody
    @PostMapping(path = "/score")
    public JsonReasult listScore(Integer eid, Integer sid, Integer uid, Integer page, Integer limit){
        try {
            HashMap<String, Integer> pageMap = new HashMap<>();
            pageMap.put("page", page);
            pageMap.put("limit", limit);
            List<Score> scores = statisticsService.listPersonalScore(eid, sid, uid, page, limit);
            long total = ((Page) scores).getTotal();
            return new JsonReasult(0,scores,null,total);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonReasult(1,"查询失败");
        }
    }


    @PostMapping(path = "/apply")
    @ResponseBody
    public JsonReasult applyStatistics(Integer uId, Integer eid, Integer sid, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date time){
        List<Apply> applies = statisticsService.listApply(uId, eid, sid, time);
        System.out.println(applies);
        return new JsonReasult(0,applies);
    }

    @PostMapping(path = "/subject")
    @ResponseBody
    public JsonReasult listSubject(){
        List<Subject> subjects = statisticsService.listSubject(null);
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

        /*HashMap<Object, Object> map = new HashMap<>();
        List<String> legend = new ArrayList<>();
        List<String> series = new ArrayList<>();


        String c = "";
        for (ExamNumberStatistics e:examSubjectNumber) {
            legend.add(e.getsName());
            c = "{value:"+e.getCount()+",name:"+e.getsName()+"}";
            series.add(c);
        }
        Object[] legend2 = legend.toArray();
        Object[] series2 = series.toArray();
        map.put("legend",legend2);
        map.put("series",series2);*/

        return new JsonReasult(0,examSubjectNumber, "各学科考试人数统计", null);
    }

    // 成绩统计
    @PostMapping(path = "/scoreStatistics")
    @ResponseBody
    public JsonReasult scoreStatistics(Integer eid, Integer sid, Integer uid){
        List<ScoreStatistics> scoreStatistics = statisticsService.listScore(eid, sid, uid);
        String msg = "";
        if (sid != null){
            msg +=  (statisticsService.listSubject(sid).get(0).getsName() + "(学科)" );
        }
        if (eid != null){
            msg += (statisticsService.listExam(eid).get(0).geteName() + "(考试)");
        }
        if (uid != null){
            msg += (statisticsService.listUser(uid).get(0).getuName()+ "(学生)");
        }
        if (sid == null && eid == null && uid == null){
            msg += "全体学科";
        }

        msg += "成绩统计";
        if(!(eid != null && uid != null)){
            msg += ",及格率" + scoreStatistics.get(0).getPassRate()+"%";
        }


        return new JsonReasult(0,scoreStatistics, msg, null);
    }
}

