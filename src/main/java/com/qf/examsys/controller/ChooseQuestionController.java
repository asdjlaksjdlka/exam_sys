package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
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
public class ChooseQuestionController {

    @Autowired
    private ChooseQuestionService chooseQuestionService;


    /**
     * 选择题控制区
     * @return
     */

    @RequestMapping("/subject/list")
    public JsonReasult findAllSubject() {

        List<Subject> list = chooseQuestionService.findAllSubject();
        System.out.println("选择题list" + list.size());
        return new JsonReasult(1, list);
    }

    @RequestMapping("/choosequestion/list")
    public JsonReasult findAllChoose(Integer page, Integer limit, String cTitle, Integer sid) {

        HashMap<String, Integer> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("limit", limit);

        List<Choose> list = chooseQuestionService.findAllChoose(pageMap, cTitle, sid);

        System.out.println("选择题控制器list==="+list);
        long total = ((Page) list).getTotal();
        return new JsonReasult(0, list, "", total);
    }

    /*
    * 回显*/
    @RequestMapping("/choosequestion/query")
    public JsonReasult findQuestion(Integer cid) {
        Choose question = chooseQuestionService.findQuestionById(cid);

        return new JsonReasult(1, question);
    }

    @PostMapping("/choosequestion/addOrUpdate")
    public JsonReasult addOrUpdateQuestion(Choose choose) {

        if (choose.getCid() == null || choose.getCid().equals("")) {
            Integer num = chooseQuestionService.addQuestion(choose);
            return new JsonReasult(1,num);
        } else {
           /* System.out.println("===="+choose.getCid().getClass().getName());*/
            Integer num = chooseQuestionService.updateQuestionById(choose);
            return new JsonReasult(1, num);
        }
    }

    @GetMapping("/question/deleteOne")
    public JsonReasult deleteOneQuestion(Choose choose) {

        Integer num = chooseQuestionService.deleteOneById(choose);
        return new JsonReasult(1,num);
    }

    @PostMapping("/question/deleteAll")
    public JsonReasult deleteAllQuestion(Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }
        Integer num = chooseQuestionService.deletAlluQestion(ids);
        return new JsonReasult(1,num);
    }


    /**
     *
     * 导入导出
     * @param cTitle
     * @param sid
     * @return
     */

    @RequestMapping(value = "/choosequestion/exportExcel", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportExcel(String cTitle, Integer sid){

        List<Choose> list = chooseQuestionService.findAllChooseByPoi(cTitle, sid);
        ResponseEntity<byte[]> info = PoiUtils.exportEmp2Excel(list);

        return info;
    }

    @RequestMapping(value = "/choosequestion/importEmp", method = RequestMethod.POST)
    @ResponseBody
    public JsonReasult importEmp(MultipartFile file) {
        List<Subject> list = chooseQuestionService.findAllSubject();
        System.out.println("list"+list);
        List<Choose> chooses = PoiUtils.importEmp2List(file,list);
        if (chooseQuestionService.addQuestionChoose(chooses) == chooses.size()) {
            return new JsonReasult (1,"导入成功!");
        }
        return new JsonReasult(0,"导入失败!");
    }



}