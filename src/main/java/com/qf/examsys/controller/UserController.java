package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("userList.do")
    @ResponseBody
    public JsonReasult userList(Integer page,Integer limit){
        PageInfo<User> info = userService.findAllUserList(page,limit);
        return new JsonReasult(0,info.getList(),"",(int)info.getTotal());
    }

    @RequestMapping("/updateUserStatus.do")
    @ResponseBody
    public JsonReasult updateUserStatus(Integer uid,Integer status){
        userService.updateUserStatus(uid,status);
        return new JsonReasult(0,null);

    }
}
