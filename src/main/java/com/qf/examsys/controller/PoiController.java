package com.qf.examsys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.PoiService;
import com.qf.examsys.service.QuestionService;
import com.qf.examsys.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


@Controller
public class PoiController {

    @Autowired
    private PoiService poiService;

    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public  ResponseEntity<byte[]> exportExcel(String cTitle, Integer sid){

        List<Choose> list = poiService.findAllChooseByPoi(cTitle, sid);
        ResponseEntity<byte[]> info = PoiUtils.exportEmp2Excel(list);

        return info;

    }

    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    @ResponseBody
    public JsonReasult importEmp(MultipartFile file) {
        List<Subject> list = questionService.findAllSubject();
        System.out.println("list"+list);
        List<Choose> chooses = PoiUtils.importEmp2List(file,list);
        if (poiService.addQuestionChoose(chooses) == chooses.size()) {
            return new JsonReasult (1,"导入成功!");
        }
        return new JsonReasult(0,"导入失败!");
    }

    @RequestMapping("/import/excel")
    @ResponseBody
    public JsonReasult importExcel(@RequestParam("file") MultipartFile upfile){

        // 获取上传文件的输入流对象
        try {
            InputStream inputStream = upfile.getInputStream();

            String filename = upfile.getOriginalFilename();
            System.out.println(filename);

            List<Map<String, Object>> list = PoiUtils.readExcel(filename, inputStream);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(list);
            // 将json格式的字符串转为指定类型的对象
            List<Choose> questionList = objectMapper.readValue(jsonStr, new TypeReference<List<Choose>>() {
            });

            poiService.addChooses(questionList);
            System.out.println(questionList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JsonReasult<>(1, "导入成功！");
    }

}
