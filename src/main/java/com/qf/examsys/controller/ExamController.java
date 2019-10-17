package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Exam;
import com.qf.examsys.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExamController {
    @Autowired
    private ExamService examService;

    @RequestMapping("/allExam.do")
    public JsonReasult allExam(){
        List<Exam> exams = examService.allExam();
        return new JsonReasult(0, exams);
    }

    @RequestMapping("/addTime.do")
    public JsonReasult addTime(Exam exam){
        int i = examService.addTime(exam);
        return new JsonReasult(0, null);
    }

    @RequestMapping("/delTime.do")
    public JsonReasult delTimeById(Integer eId){
        int i = examService.delTimeById(eId);
        return new JsonReasult(0, null);
    }

    @RequestMapping("/findExam.do")
    public JsonReasult findExam(Integer eId){
        Exam exam = examService.findExamById(eId);
        return new JsonReasult(0, exam);
    }

    @RequestMapping("/updExam.do")
    public JsonReasult uptExam(Exam exam){
        int i = examService.updExam(exam);
        return new JsonReasult(0, null);
    }
}
