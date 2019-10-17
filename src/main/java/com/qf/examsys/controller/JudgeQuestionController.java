package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.ChooseQuestionService;
import com.qf.examsys.service.JudgeQuestionService;
import com.qf.examsys.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Controller
@ResponseBody
public class JudgeQuestionController {

    @Autowired
    private JudgeQuestionService judgeQuestionService;

    @Autowired
    private ChooseQuestionService chooseQuestionService;

    @RequestMapping("/judgequestion/list")
    public JsonReasult findAllJudge(Integer page, Integer limit, String jTitle, Integer sid){

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page",page);
        pageMap.put("limit",limit);

        List<Judge> list = judgeQuestionService.findAllJudge(pageMap, jTitle, sid);

        long total = ((Page) list).getTotal();
        return new JsonReasult(0, list, "", total);
    }


    /*
     * 回显*/
    @RequestMapping("/judgequestion/query")
    public JsonReasult findjudge(Integer jid) {
        Judge judge = judgeQuestionService.findJudgeQuestionById(jid);
        return new JsonReasult(1, judge);
    }

    @PostMapping("/judgequestion/addOrUpdate")
    public JsonReasult addOrUpdateQuestion(Judge judge) {

        System.out.println("哈哈"+judge);
        if (judge.getJid() == null ||judge.getJid().equals("")) {
            Integer num = judgeQuestionService.addJudge(judge);
            return new JsonReasult(1, num);
        } else {
            /* System.out.println("===="+choose.getCid().getClass().getName());*/
            Integer num = judgeQuestionService.updateJudgeById(judge);
            return new JsonReasult(1, num);
        }
    }


    @GetMapping("/judgequestion/deleteOne")
    public JsonReasult deleteOneQuestion(Judge judge) {
        Integer num = judgeQuestionService.deleteOneById(judge);

//        System.out.println("哈哈"+num);
        return new JsonReasult(1,num);
    }

    @PostMapping("/judgequestion/deleteAll")
    public JsonReasult deleteAllBrief(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        Integer num = judgeQuestionService.deletAlljudge(ids);
        return new JsonReasult(1,num);
    }


    /**
     *
     * 导入导出
     * @param jTitle
     * @param sid
     * @return
     */

    @RequestMapping(value = "/judgequestion/exportExcel", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportExcel(String jTitle, Integer sid){
        List<Judge> list = judgeQuestionService.findAllBriefByPoi(jTitle, sid);
        ResponseEntity<byte[]> info = PoiUtils.exportJudgeExcel(list);
        return info;
    }

    @RequestMapping(value = "/judgequestion/importbrief", method = RequestMethod.POST)
    @ResponseBody
    public JsonReasult importEmp(MultipartFile file) {

        List<Subject> list = chooseQuestionService.findAllSubject();
        System.out.println("list"+list);

        List<Judge> judges = PoiUtils.importJudgeList(file, list);

        if (judgeQuestionService.addJudges(judges) == judges.size()) {
            return new JsonReasult (1,"导入成功!");
        }
        return new JsonReasult(0,"导入失败!");
    }

}
