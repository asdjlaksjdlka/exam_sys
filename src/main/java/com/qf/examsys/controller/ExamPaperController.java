package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Record;
import com.qf.examsys.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.jar.JarEntry;

public class ExamPaperController {
    @Autowired
    ExamPaperService examPaperService;
    @RequestMapping("/findExamPaper.do")
    public JsonReasult findExamPaper(Integer uid){
        List<Record> uid1 = examPaperService.findExamPaperByUid(uid);
        return new JsonReasult(1,uid1);
    }
}
