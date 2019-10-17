package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.BriefQuestionService;
import com.qf.examsys.service.ChooseQuestionService;
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
public class BriefQuestionController {

    @Autowired
    private BriefQuestionService briefQuestionService;

    @Autowired
    private ChooseQuestionService chooseQuestionService;
    /**
     * 简答题控制区
     * @return
     */
    @RequestMapping("/briefquestion/list")
    public JsonReasult findAllBrief(Integer page, Integer limit, String bTitle, Integer sid) {

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);
        List<Brief> list = briefQuestionService.findAllBrief(pageMap,bTitle, sid);
//        System.out.println("list"+list);
        long total = ((Page) list).getTotal();
        return new JsonReasult(0, list, "", total);
    }


    /*
     * 回显*/
    @RequestMapping("/briefquestion/query")
    public JsonReasult findQuestion(Integer bid) {

        Brief brief = briefQuestionService.findBriefQuestionById(bid);

        return new JsonReasult(1, brief);
    }

    @PostMapping("/briefquestion/addOrUpdate")
    public JsonReasult addOrUpdateQuestion(Brief brief) {

//        System.out.println("哈哈"+brief);
        if (brief.getBid() == null || brief.getBid().equals("")) {
            Integer num = briefQuestionService.addBrief(brief);
            return new JsonReasult(1,num);
        } else {
            /* System.out.println("===="+choose.getCid().getClass().getName());*/
            Integer num = briefQuestionService.updateBriefById(brief);
            return new JsonReasult(1, num);
        }
    }

    @GetMapping("/briefquestion/deleteOne")
    public JsonReasult deleteOneQuestion(Brief brief) {
        Integer num = briefQuestionService.deleteOneById(brief);
//        System.out.println("哈哈"+num);
        return new JsonReasult(1,num);
    }


    @PostMapping("/briefquestion/deleteAll")
    public JsonReasult deleteAllBrief(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        Integer num = briefQuestionService.deletAllBrief(ids);
        return new JsonReasult(1,num);
    }


    /**
     *
     * 导入导出
     * @param bTitle
     * @param sid
     * @return
     */

    @RequestMapping(value = "/briefquestion/exportExcel", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportExcel(String bTitle, Integer sid){
        List<Brief> list = briefQuestionService.findAllBriefByPoi(bTitle, sid);
        ResponseEntity<byte[]> info = PoiUtils.exportBriefExcel(list);

        return info;
    }

    @RequestMapping(value = "/briefquestion/importbrief", method = RequestMethod.POST)
    @ResponseBody
    public JsonReasult importEmp(MultipartFile file) {

        List<Subject> list = chooseQuestionService.findAllSubject();
        System.out.println("list"+list);
        List<Brief> briefs = PoiUtils.importBriefList(file,list);
        if (briefQuestionService.addBrief(briefs) == briefs.size()) {
            return new JsonReasult (1,"导入成功!");
        }
        return new JsonReasult(0,"导入失败!");
    }


}
