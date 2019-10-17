package com.qf.examsys;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Score;
import com.qf.examsys.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamsysApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void test1(){
        List<Score> scores = statisticsService.listPersonalScore(1,2,1);
        JsonReasult jsonReasult = new JsonReasult(0, scores);
        System.out.println(jsonReasult);
        System.out.println("" +
                "JsonReasult{code=0, " +
                "data=[Score{scId=3, " +
                "user=User{uid=1, uPhone='null', uPassword='null', uName='shi', uStatus='null'}, " +
                "subject=Subject{sid=2, sName='Python'}," +
                "exam=Exam{eId=1, 考试名name='第一次考试', 开始时间beginTime=Fri Oct 25 00:00:00 CST 2019, 结束时间endTime=Fri Oct 25 00:00:00 CST 2019, 试卷pid=1}, " +
                "chooseScore=20, judgeScore=20, briefScore=20, totalScore=60}]," +
                "msg='null', count=null}\n");
    }


}
