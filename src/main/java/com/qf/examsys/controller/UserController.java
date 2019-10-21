package com.qf.examsys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import com.qf.examsys.utils.SendSms;
import org.springframework.beans.NotReadablePropertyException;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    UserService userService;
    //注入 redis
    @Autowired
    private StringRedisTemplate redisTemplate;

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


    //查看个人成绩
    @CrossOrigin    //解决跨域问题
    @RequestMapping("/selectScoreById")
    @ResponseBody
    public JsonReasult selectScoreById(Integer uid){
        List<Score> scores = userService.selectScoreById(uid);
        return new JsonReasult(1,scores);
    }

    /**
     * 获取短信验证码
     * @param    uPhone 用户填写手机号
     * @return  验证码发送成功提示
     */
    @RequestMapping("/getCode.do")
    public JsonReasult getCode(String uPhone){

        //判断是否填写手机号
        if (uPhone == null){
            //手机号为空，返回失败提示
            return new JsonReasult(0,"请输入手机号");
        }
        //手机号是否合法
        if (!uPhone.matches("^[1-9a-zA-Z]{3,12}$")){
            return new JsonReasult(0,"手机号不合法，请重新输入，，");
        }
        //将用的短信验证码保存到redis中
        String validateCode = SendSms.sendMessage(uPhone);
        //  判断获取短信验证码是否出现异常
        if (validateCode.length() != 6) {
            //  说明返回的不是验证码，而是异常信息
            return new JsonReasult(0, validateCode);
        }

        //  将短信验证码保存到 Redis 中，有效期为5分钟
        //  key  ： 手机号码
        //  value： 验证码
        redisTemplate.opsForValue().set(uPhone, validateCode);
        redisTemplate.expire(uPhone, 300, TimeUnit.SECONDS);

        return new JsonReasult(1, "发送成功，请查收！");
    }

    /**
     *          注册账号时，在用户输入用户名之后，输入框失去焦点后立即查询数据库中是否存在此用户名
     *
     * @Author imlee
     * @param username
     * @return      返回查询结果、提示信息
     */
    @RequestMapping("/checkName.do")
    public JsonReasult checkName(String username) {

        User user = userService.login(username);
        if (user == null) {

            if (username.matches("^[1-9a-zA-Z]{3,12}$")) {

                return new JsonReasult(1, "用户名可以使用！");
            } else {

                return new JsonReasult(0, "用户名不合法！");
            }

        }

        return new JsonReasult(0, "用户名已存在！");
    }

    /**
     *          注册账号第一步：使用手机号码进行预注册
     *
     * @Author  imlee
     *
     * @param telephoneNumber   用户填写的电话号码
     * @param telephoneCode     短信验证码
     * @return
     */
    @RequestMapping("/signUpFirst.do")
    public JsonReasult signUp(String telephoneNumber, String telephoneCode) {

        User user = userService.signInByTelephoneNumber(telephoneNumber);
        if (user != null) {
            return new JsonReasult(0, "此号码已注册,请直接登录！");
        }

        //  按照 手机号码 + 短信验证码 注册
        //      获取 保存在 redis 中的短信验证码
        String code = redisTemplate.opsForValue().get(telephoneNumber);

        //  判断短信验证码是否过期
        if (code == null || code.equals("")) {
            return new JsonReasult(0, "验证码已过期，请重新获取！");
        } else {
            //  验证 用户输入的验证码和redis中保存的验证码是否一致
            if (telephoneCode.equals(code)) {

                //  验证码正确，执行注册逻辑
                int i = userService.signUpFirst(telephoneNumber);

                if (i != 1) {
                    //  返回受影响行数，不为1，即注册失败
                    return new JsonReasult(0, "注册失败！");
                }

            } else {
                //  验证码错误，返回提示
                return new JsonReasult(0, "验证码输入有误，请重新输入！");
            }
        }
    @Autowired
    UserService userService;

    @RequestMapping("userList.do")
    @ResponseBody
    public JsonReasult userList(Integer page,Integer limit){
       List<User> list = userService.findAllUserList(page,limit);
        long total = ((Page) list).getTotal();
        return new JsonReasult(0,list,"",total);
    }

    @RequestMapping("/updateUserStatus.do")
    @ResponseBody
    public JsonReasult updateUserStatus(Integer uid,Integer status){
        userService.updateUserStatus(uid,status);
        return new JsonReasult(0,"");

    }
}
        return new JsonReasult(1, telephoneNumber);
    }

    /**
     *          注册账号第二步：设置用户名和密码
     *
     * @Author  imlee
     * @param username          用户填写的用户名
     * @param password          用户填写的密码
     * @param telephoneNumber   用户填写的电话号码
     * @param validate          用户填写的验证码
     * @return
     */
    @RequestMapping("/signUp.do")
    public JsonReasult signUp(String username, String password, String telephoneNumber, String validate) {

        //  获取 redis 中保存的验证码文字
        String codeStr = redisTemplate.opsForValue().get("codeStr");

        //  首先验证 验证码（忽略大小写）
        if (validate.equalsIgnoreCase(codeStr)) {

            //  执行注册逻辑
            //  先查询用户名是否存在
            User user = userService.login(username);

            //  判断用户名是否存在
            if (user != null) {
                //  用户名已存在
                return new JsonReasult(0, "此用户名已存在，请使用其它用户名！");
            } else {
                //  用户名不存在，进行注册
                int i = userService.signUp(username, password, telephoneNumber);

                if (i != 1) {
                    //  返回受影响行数，不为1，即注册失败
                    return new JsonReasult(0, "注册失败！");
                }
            }
        } else {

            //  验证码错误，返回提示
            return new JsonReasult(0, "验证码输入有误，请重新输入！");
        }

        return new JsonReasult(1, "注册成功！");
    }

    /**
     *      登录方法    1.使用账号 + 密码 + 文字验证码登录
     *                 2.使用手机号码 + 短信验证码登录
     *
     * @Author  imlee
     *
     * @param username          用户名
     * @param password          密码
     * @param validate          验证码
     * @param telephoneNumber   手机号码
     * @param telephoneCode     短信验证码
     * @return                  供前台使用的 Json 数据
     */
    @RequestMapping("/signIn.do")
    public JsonReasult signIn(String username, String password, String validate, String telephoneNumber, String telephoneCode) {

        //  验证码非空或者不是null，说明使用的是账号 + 密码 + 验证码
        //      反之，说明使用的是，手机号码 + 短信验证码

        User user = null;

        if (validate != null && !validate.equals("")) {

            //  获取 redis 中保存的验证码文字
            String codeStr = redisTemplate.opsForValue().get("codeStr");

            //  首先验证 验证码（忽略大小写）
            //      验证码正确，再验证，账号、密码
            if (validate.equalsIgnoreCase(codeStr)) {

                //  执行登录逻辑
                user = userService.login(username);

                //  判断用户名是否存在
                if (user == null) {
                    return new JsonReasult(0, "此用户名不存在，请核对后重新登录！");
                } else {

                    //  比对用户输入的密码和数据库中是否一致
                    if (!password.equals(user.getuPassword())) {
                        return new JsonReasult(0, "密码错误，请核对密码或用户名是否输入有误！");
                    }
                }
            } else {

                //  验证码错误，返回提示
                return new JsonReasult(0, "验证码输入有误，请重新输入！");
            }
        } else {

            //  按照 手机号码 + 短信验证码 的逻辑登录
            //      获取 保存在 redis 中的短信验证码
            String code = redisTemplate.opsForValue().get(telephoneNumber);

            //  判断短信验证码是否过期
            if (code == null || code.equals("")) {
                return new JsonReasult(0, "验证码已过期，请重新获取！");
            }

            //  验证 用户输入的验证码和redis中保存的验证码是否一致
            if (telephoneCode.equals(code)) {

                //  验证码正确，执行登录逻辑
                user = userService.signInByTelephoneNumber(telephoneNumber);

                if (user == null) {
                    return new JsonReasult(0, "此号码未注册账号，如需注册请移步注册页面(验证码有效期5分钟)！");
                }

            } else {
                //  验证码错误，返回提示
                return new JsonReasult(0, "验证码输入有误，请重新输入！");
            }
        }

        //  登录成功，返回 user 对象
        return new JsonReasult(1, user);
    }

    /**
     *          重置密码：第一步，查询手机号码是否存在
     *
     * @Author  imlee
     * @param telephoneNumber   手机号码
     * @param telephoneCode     短信验证码
     * @return
     */
    @RequestMapping("/resetPasswordFirst.do")
    public JsonReasult resetPasswordFirst(String telephoneNumber, String telephoneCode) {


        User user = userService.signInByTelephoneNumber(telephoneNumber);
        if (user == null) {
            return new JsonReasult(0, "此号码未注册,请核对是否输入有误！");
        }

        //  按照 手机号码 + 短信验证码 重置密码
        //      获取 保存在 redis 中的短信验证码
        String code = redisTemplate.opsForValue().get(telephoneNumber);

        //  判断短信验证码是否过期
        if (code == null || code.equals("")) {

            return new JsonReasult(0, "验证码已过期，请重新获取！");

        } else {
            //  验证 用户输入的验证码和redis中保存的验证码是否一致
            if (!telephoneCode.equals(code)) {
                return new JsonReasult(0, "验证码输入有误，请重新输入！");
            }
        }

        return new JsonReasult(1, telephoneNumber);
    }

    /**
     *          重置密码：第二步，修改密码
     *
     * @Author imlee
     * @param password          新密码
     * @param validate          验证码
     * @param telephoneNumber   手机号码
     * @return
     */
    @RequestMapping("/resetPassword.do")
    public JsonReasult resetPassword(String password, String validate, String telephoneNumber) {

        //  获取 redis 中保存的验证码文字
        String codeStr = redisTemplate.opsForValue().get("codeStr");

        //  首先验证 验证码（忽略大小写）
        if (validate.equalsIgnoreCase(codeStr)) {

            //  执行重置密码逻辑
            int i = userService.resetPassword(password, telephoneNumber);

            if (i != 1) {
                return new JsonReasult(0, "重置失败！");
            }

        } else {
            //  验证码错误，返回提示
            return new JsonReasult(0, "验证码输入有误，请重新输入！");
        }

        return new JsonReasult(1, "重置成功！");
    }


}





