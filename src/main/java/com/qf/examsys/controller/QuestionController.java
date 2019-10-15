package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public JsonReasult findAllSubject(){

        List<Subject> list = questionService.findAllSubject();
//        System.out.println("list"+list);

        return new JsonReasult(1,list);
    }

    @RequestMapping("/choosequestion/list")
    public JsonReasult findAllChoose(Integer page, Integer limit, String cTitle,Integer sid){

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Choose> list = questionService.findAllChoose(pageMap, cTitle,sid);

//        System.out.println("list"+list);
        long total = ((Page) list).getTotal();


        return new JsonReasult(0,list,"",total);
    }


}
