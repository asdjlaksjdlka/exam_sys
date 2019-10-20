package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.service.GetExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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


    /**
     * 从redis取试卷
     *
     * @param sid
     * @param uid
     * @param eid
     * @return
     */
    @RequestMapping("/examsys/getTestMapper")
    @ResponseBody
    public HashMap<String, List> GetExamPaper(Integer sid, Integer uid, Integer eid) {

        /**
         * 手动获取redis数据，service必须手动存入
         */
        /*HashMap<String, List> questionMap = getExamPaperService.makePaper(sid, uid, eid);
        HashMap<String, List> questionMap1 = (HashMap) redisTemplate.opsForHash().get(sid + uid + eid, uid);*/

        //利用springboot的缓存

        HashMap<String, List> paper = getExamPaperService.getPaper(uid, sid, eid);
        // System.out.println(questionMap1.size());
        return paper;
    }

    /**
     * 生成试卷并存入redis
     *
     * @param sid
     * @param uid
     * @param eid
     * @return
     */
    @RequestMapping("/examsys/makeTestMapper")
    @ResponseBody
    public JsonReasult makeExamPaper(Integer sid, Integer uid, Integer eid) {

        HashMap<String, List> questionMap = getExamPaperService.makePaper(sid, uid, eid);

        return new JsonReasult(1, questionMap);
    }


    /**
     * 用于保存每次单选的单击试卷，目前无用
     * @param uid
     * @param eid
     * @param oneAnswer
     * @return
     */
    @RequestMapping("/examsys/savaExamOneAnswer")
    @ResponseBody
    public JsonReasult savaExamOneAnswer(Integer uid,Integer eid,String oneAnswer) {

        Integer num = getExamPaperService.savaExamOneAnswer(uid, eid, oneAnswer);


        return null;
    }

    /**
     * 交卷
     * @param uid
     * @param eid
     * @param TestAnswer
     * @param shortAnswer
     * @return
     */
    @RequestMapping("/examsys/saveExamAnswer")
    @ResponseBody
    public JsonReasult saveExamAnswer(Integer uid,Integer eid,String TestAnswer,String shortAnswer) {

        Integer integer = getExamPaperService.saveExamAnswer(uid, eid, TestAnswer, shortAnswer);
        return new JsonReasult(1,integer,"交卷成功");
    }

}
