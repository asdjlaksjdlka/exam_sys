package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;


@Controller
@ResponseBody
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/subject/list")
    public JsonReasult findAllSubject() {

        List<Subject> list = questionService.findAllSubject();
        System.out.println("list" + list.size());

        return new JsonReasult(1, list);
    }

    @RequestMapping("/choosequestion/list")
    public JsonReasult findAllChoose(Integer page, Integer limit, String cTitle, Integer sid) {

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Choose> list = questionService.findAllChoose(pageMap, cTitle, sid);

//        System.out.println("list"+list);
        long total = ((Page) list).getTotal();
        return new JsonReasult(0, list, "", total);
    }

    @RequestMapping("/question/query")
    public JsonReasult findQuestion(Integer cid) {
        Choose question = questionService.findQuestionById(cid);

        return new JsonReasult(1, question);
    }

    @PostMapping("/question/addOrUpdate")
    public JsonReasult addOrUpdateQuestion(Choose choose) {

        if (choose.getCid() == null || choose.getCid().equals("")) {
            Integer num = questionService.addQuestion(choose);
            return new JsonReasult(1,num);
        } else {
           /* System.out.println("===="+choose.getCid().getClass().getName());*/
            Integer num = questionService.updateQuestionById(choose);
            return new JsonReasult(1, num);
        }
    }

    @GetMapping("/question/deleteOne")
    public JsonReasult deleteOneQuestion(Choose choose) {

        Integer num = questionService.deleteOneById(choose);
        return new JsonReasult(1,num);
    }

    @PostMapping("/question/deleteAll")
    public JsonReasult deleteAllQuestion(Integer[] ids) {

        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        Integer num = questionService.deletAlluQestion(ids);

        return new JsonReasult(1,num);
    }
}