package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.service.JudgeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    @RequestMapping("/judgequestion/list")
    public JsonReasult findAllJudge(Integer page, Integer limit, String bTitle, Integer sid){

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page",page);
        pageMap.put("limit",limit);

        List<Judge> list = judgeQuestionService.findAllJudge(pageMap, bTitle, sid);

        long total = ((Page) list).getTotal();
        return new JsonReasult(0, list, "", total);
    }
}
