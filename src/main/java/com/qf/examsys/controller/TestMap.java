package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ Description   :
 * @ Author        :  yqz
 * @ CreateDate    :  2019/11/26 14:14
 */
@RestController
public class TestMap {

    @PostMapping("/testmap")
    public JsonReasult updatePersonPassword(@RequestBody Map parms) {

//        Map firstName = (Map) parms.get("employees");
//        System.out.println(firstName+"哈哈");
//        System.out.println(firstName.get(1)+"嘿嘿");
        System.out.println("哈哈哈"+parms);
        return new JsonReasult(1, "修改成功");

    }
}
