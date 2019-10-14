package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/subject/list")
    public JsonReasult findAllSubject(){

        List<Subject> list = questionService.findAllSubject();
        System.out.println("list"+list);

        return new JsonReasult(1,list);
    }



}
