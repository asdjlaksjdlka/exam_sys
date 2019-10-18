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
    private Record record;

    @Autowired
    private GetExamPaperDao getExamPaperDao;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 生成试卷并存入redis
     * @param sid
     * @param uid
     * @param eid
     * @return
     */
    //进行缓存，把方法的返回值进行缓存
    @Cacheable(key = "'exam'.concat(#uid).concat(#sid).concat(#eid)")
    @Override
    public HashMap<String, List> makePaper(Integer sid, Integer uid ,Integer eid) {
        ArrayList<Record> recordList = new ArrayList<>();

        HashMap<String, List> questionMap = new HashMap<>();

        //获取选择题
        List<Choose> chooseByRand = getExamPaperDao.getChooseByRand(sid);
        //获取判断题
        List<Judge> judgeByRand = getExamPaperDao.getJudgeByRand(sid);
        //获取简答题
        List<Brief> briefByRand = getExamPaperDao.getBriefByRand(sid);


        questionMap.put("chooseQuestion",chooseByRand);
        questionMap.put("judgeQuestion",judgeByRand);
        questionMap.put("briefQuestion",briefByRand);

        //redisTemplate.opsForHash().put(sid + uid + eid,uid,questionMap);

        return questionMap;
    }

    @Cacheable(key = "'exam'.concat(#uid).concat(#sid).concat(#eid)")
    @Override
    public HashMap<String, List> getPaper(Integer uid, Integer eid, Integer sid) {
        return null;
    }
}
