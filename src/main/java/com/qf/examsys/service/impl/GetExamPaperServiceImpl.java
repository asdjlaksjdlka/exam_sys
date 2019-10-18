package com.qf.examsys.service.impl;

import com.qf.examsys.dao.GetExamPaperDao;
import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.entity.Record;
import com.qf.examsys.service.GetExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

//用在类上，设置缓存的名称
@CacheConfig(cacheNames = "paper")
@Service
public class GetExamPaperServiceImpl implements GetExamPaperService {


    @Autowired(required = false)
    private GetExamPaperDao getExamPaperDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Record record;

    /**
     * 生成试卷并存入redis
     *
     * @param sid
     * @param uid
     * @param eid
     * @return
     */
    //进行缓存，把方法的返回值进行缓存
    @Cacheable(key = "'exam'.concat(#uid).concat(#sid).concat(#eid)")
    @Override
    public HashMap<String, List> makePaper(Integer sid, Integer uid, Integer eid) {
        ArrayList<Record> recordList = new ArrayList<>();

        HashMap<String, List> questionMap = new HashMap<>();

        //获取选择题
        List<Choose> chooseByRand = getExamPaperDao.getChooseByRand(sid);
        //获取判断题
        List<Judge> judgeByRand = getExamPaperDao.getJudgeByRand(sid);
        //获取简答题
        List<Brief> briefByRand = getExamPaperDao.getBriefByRand(sid);


        questionMap.put("chooseQuestion", chooseByRand);
        questionMap.put("judgeQuestion", judgeByRand);
        questionMap.put("briefQuestion", briefByRand);

        /*
         * 手动把生成的数据存入数redis*/
        //redisTemplate.opsForHash().put(sid + uid + eid,uid,questionMap);

        return questionMap;
    }

    /**
     * 从缓存取试卷
     *
     * @param uid
     * @param eid
     * @param sid
     * @return
     */
    @Cacheable(key = "'exam'.concat(#uid).concat(#sid).concat(#eid)")
    @Override
    public HashMap<String, List> getPaper(Integer uid, Integer eid, Integer sid) {
        return null;
    }

    /**
     * 用于保存每次单选的单击试卷，目前无用
     *
     * @param uid
     * @param eid
     * @param oneAnswer
     * @return
     */
    @Override
    public Integer savaExamOneAnswer(Integer uid, Integer eid, String oneAnswer) {
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<Integer> sidList = new ArrayList<>();
        ArrayList<String> rAnswerList = new ArrayList<>();

        HashSet<String> titleMap = new HashSet<>();


        String title = oneAnswer.split("-")[0];
        titleList.add(title);

        Integer sid = Integer.valueOf(oneAnswer.split("-")[1]);
        sidList.add(sid);

        String rAnswer = oneAnswer.split("-")[2];
        rAnswerList.add(rAnswer);


        System.out.println(titleList + ":" + sidList + ":" + rAnswerList);
        return null;
    }

    @Override
    public Integer saveExamAnswer(Integer uid, Integer eid, String TestAnswer, String shortAnswer) {

        String[] chooseAndJudge = TestAnswer.split("&");

        String[] briefs = shortAnswer.split("&");


        List<Record> recordList = new ArrayList<>();

        /**
         * 选择判断
         */
        for (int i = 0; i < chooseAndJudge.length; i++) {
            //单选
            if (chooseAndJudge[i].contains("RR")) {
                /**
                 * 遍历取值赋值
                 */
                String title = chooseAndJudge[i].split("-")[1];
                Integer sid = Integer.valueOf(chooseAndJudge[i].split("-")[2]);
                String rAnswer = chooseAndJudge[i].split("-")[3];
                Integer rScore = Integer.valueOf(chooseAndJudge[i].split("-")[4]);
                String uAnswer = chooseAndJudge[i].split("-")[5];

                record.setUid(uid);
                record.setTitle(title);
                record.setSid(sid);
                record.setrAnswer(rAnswer);
                record.setuAnswer(uAnswer);
                record.setEid(eid);
                record.setrScore(rScore);
                recordList.add(record);
            }
            //判断
            if (chooseAndJudge[i].contains("JJ")) {
//                System.out.println("---分割线--------" + chooseAndJudge[i]);

                /**
                 * 遍历取值赋值
                 */
                String title = chooseAndJudge[i].split("-")[1];
                Integer sid = Integer.valueOf(chooseAndJudge[i].split("-")[2]);
                String rAnswer = chooseAndJudge[i].split("-")[3];
                Integer rScore = Integer.valueOf(chooseAndJudge[i].split("-")[4]);
                String uAnswer = chooseAndJudge[i].split("-")[5];

                record.setUid(uid);
                record.setTitle(title);
                record.setSid(sid);
                record.setrAnswer(rAnswer);
                record.setuAnswer(uAnswer);
                record.setEid(eid);
                record.setrScore(rScore);
                recordList.add(record);

            }
        }

        /**
         * 简答题
         */
        for (int i = 0; i < briefs.length; i++) {
            System.out.println("---分割线--------" + briefs[i]);

            /**
             * 遍历取值赋值
             */
            String test = briefs[i].split("--")[0];


            String title = briefs[i].split("--")[1];
            System.out.println("title"+title);
            Integer sid = Integer.valueOf(briefs[i].split("--")[2]);
            String rAnswer = briefs[i].split("--")[3];
            Integer rScore = Integer.valueOf(briefs[i].split("--")[4]);
            String uAnswer = briefs[i].split("--")[5];

            record.setUid(uid);
            record.setTitle(title);
            record.setSid(sid);
            record.setrAnswer(rAnswer);
            record.setuAnswer(uAnswer);
            record.setEid(eid);
            record.setrScore(rScore);
            recordList.add(record);
        }


        Integer integer = getExamPaperDao.commitExam(recordList);

        for (String s : chooseAndJudge) {
            System.out.println("哈哈" + s);
        }


        System.out.println(recordList.size() + "============");
        return integer;
    }
}
