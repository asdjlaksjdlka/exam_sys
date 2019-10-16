package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.NotReadablePropertyException;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class UserController {

    @Autowired(required = false)
    UserService userService;

    //登录
    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public JsonReasult login(String uPhone,String uPassword){
        User user = userService.login(uPhone);
        String p = user.getuPassword();
        if (uPassword.equals(p)){
            return new JsonReasult(1,user);
        }else{
            return new JsonReasult(0,"账号或者密码错误，请重试");
        }

    }

    //注册学生
    @CrossOrigin
    @RequestMapping("/addUser")
    @ResponseBody
    public JsonReasult insert(User user){
        //添加用户

        user.setRoId(1);
        userService.insert(user);
        return new JsonReasult(1,"注册成功");
    }



    //注册老师，管理员给老师注册
    @CrossOrigin
    @RequestMapping("/addTeacher")
    @ResponseBody
    public JsonReasult insertTeacher(User user){
                user.setRoId(2);
                userService.insert(user);
                return new JsonReasult(1, "注册成功");
    }



    //通过id查看用户个人信息
    @CrossOrigin
    @RequestMapping("/findUserById.do")
    @ResponseBody
    public JsonReasult findUserById(Integer uid){
        User u = userService.findByUserId(uid);
        return new JsonReasult(1,u);
    }



    //重置个人密码
    @CrossOrigin
    @RequestMapping("/updatePersonPassword")
    @ResponseBody
    public JsonReasult updatePersonPassword(Integer uid,String uPassword){
//        System.out.println(uid);
        User user = userService.findByUserId(uid);
//        System.out.println(user);
        user.setuPassword(uPassword);
        userService.updatePersonPassword(user);
        return new JsonReasult(1,"修改成功");

    }

    @CrossOrigin
    @RequestMapping("/selectScoreById")
    @ResponseBody
    public JsonReasult selectScoreById(Integer uid){
        User user = userService.selectScoreById(uid);
        return new JsonReasult(1,user);
    }

}


