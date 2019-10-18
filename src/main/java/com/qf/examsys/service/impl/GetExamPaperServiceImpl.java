package com.qf.examsys.service.impl;

import com.qf.examsys.dao.GetExamPaperDao;
import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.entity.Record;
import com.qf.examsys.service.GetExamPaperService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetExamPaperServiceImpl implements GetExamPaperService {

    @Autowired(required = false)
    private Record record;

    @Autowired
    private GetExamPaperDao getExamPaperDao;

    @Override
    public HashMap<String, List> makePaper(Integer uid, Integer eid, Integer sid) {
        ArrayList<Record> recordList = new ArrayList<>();

        HashMap<String, List> questionMap = new HashMap<>();

        //获取选择题
        List<Choose> chooseByRand = getExamPaperDao.getChooseByRand(sid);
/*        System.out.println("哈哈"+chooseByRand);
        for (Choose choose : chooseByRand) {
            System.out.println("答案"+choose.getcAnswer());
            record.setTitle(choose.getcTitle());
            record.setrAnswer(choose.getcAnswer());
            record.setUid(uid);
            record.setReid(eid);
            recordList.add(record);
        }*/

        //获取判断题
        List<Judge> judgeByRand = getExamPaperDao.getJudgeByRand(sid);
/*        for (Judge judge : judgeByRand) {
            record.setTitle(judge.getjTitle());
            record.setrAnswer(judge.getjAnswer());
            record.setUid(uid);
            record.setEid(eid);
            recordList.add(record);
        }*/
        //获取简答题
        List<Brief> briefByRand = getExamPaperDao.getBriefByRand(sid);

/*        for (Brief brief : briefByRand) {
            record.setTitle(brief.getbTitle());
            record.setrAnswer(brief.getbAnswer());
            record.setUid(uid);
            record.setEid(eid);
            recordList.add(record);
        }*/

        questionMap.put("chooseQuestion",chooseByRand);
        questionMap.put("judgeQuestion",judgeByRand);
        questionMap.put("briefQuestion",briefByRand);

        return questionMap;
    }
}
