package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Exam;
import com.qf.examsys.service.GetExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class GetExamPaperController {

    @Autowired
    private GetExamPaperService getExamPaperService;
    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/examsys/selectExamMapper")
    @ResponseBody
    public JsonReasult selectExamMapper() {
        List<Exam> examList = getExamPaperService.selectAllExam();

        return new JsonReasult(1, examList);
    }



   /* private Integer sid;
    private Integer eid;*/

    @RequestMapping("/examsys/makeTestMapper")
    @ResponseBody
    public JsonReasult makeExamPaper(String exam) {

        Integer eid = Integer.valueOf(exam.split("&sid=")[0]);
        Integer sid = Integer.valueOf(exam.split("&sid=")[1]);

//        System.out.println(eid+"哈哈"+sid);

        HashMap<String, List> questionMap = getExamPaperService.makePaper(4, eid, sid);

        return new JsonReasult(1, "获取试卷成功");
    }


    /**
     * 生成试卷并存入redis(草稿)
     */
/*    @RequestMapping("/examsys/makeTestMapper")
    @ResponseBody
    public JsonReasult makeExamPaper(Integer sid, Integer uid, Integer eid) {

        HashMap<String, List> questionMap = getExamPaperService.makePaper(sid, uid, eid);

        return new JsonReasult(1, questionMap);
    }*/
    @RequestMapping("/examsys/getTestMapper")
    @ResponseBody
    public HashMap<String, List> GetExamPaper(Integer eid, Integer sid) {

        //HashMap<String, List> questionMap = getExamPaperService.makePaper(sid, uid, eid);
        //HashMap<String, List> questionMap1 = (HashMap) redisTemplate.opsForHash().get(sid + uid + eid, uid);

        //利用springboot的缓存

        HashMap<String, List> paper = getExamPaperService.getPaper(4, eid, sid);

        // System.out.println(questionMap1.size());

        return paper;

    }


    /**
     * 用于保存每次单选的单击试卷，目前无用
     *
     * @param uid
     * @param eid
     * @param oneAnswer
     * @return
     */
    @RequestMapping("/examsys/savaExamOneAnswer")
    @ResponseBody
    public JsonReasult savaExamOneAnswer(Integer uid, Integer eid, String oneAnswer) {

        Integer num = getExamPaperService.savaExamOneAnswer(uid, eid, oneAnswer);


        return null;
    }

    /**
     * 交卷
     *
     * @param uid
     * @param eid
     * @param TestAnswer
     * @param shortAnswer
     * @return
     */
    @RequestMapping("/examsys/saveExamAnswer")
    @ResponseBody
    public JsonReasult saveExamAnswer(Integer uid, Integer eid, String TestAnswer, String shortAnswer) {

        Integer integer = getExamPaperService.saveExamAnswer(uid, eid, TestAnswer, shortAnswer);
        return new JsonReasult(1, integer, "交卷成功");
    }

}
