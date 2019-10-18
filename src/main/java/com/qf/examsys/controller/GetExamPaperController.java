package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.service.GetExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class GetExamPaperController {

    @Autowired
    private GetExamPaperService getExamPaperService;

    @RequestMapping("/examsys/getTestMapper")
    @ResponseBody
    public JsonReasult GetExamPaper(Integer sid, Integer uid, Integer eid){

        HashMap<String, List> questionMap = getExamPaperService.makePaper(sid, uid, eid);

        return new JsonReasult(1,questionMap);
    }
}
