package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {

    @Autowired(required = false)
    UserService userService;


    //注册学生
    @CrossOrigin
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonReasult insert(User record){
        record.setUid(1);
        userService.insert(record);
        return new JsonReasult(1,"添加成功");
    }

    //注册老师，管理员给老师注册
    @CrossOrigin
    @RequestMapping("/addTeacher")
    @ResponseBody
    public JsonReasult insertTeacher(User record){
        record.setUid(2);
        userService.insert(record);
        return new JsonReasult(1,"注册成功");
    }


}
