package com.qf.examsys.service;

import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qf.examsys.entity.User;

import java.util.List;

public interface UserService {

    //授权
    public User findUserByPhone(String name);

    void insert(User user);
    int insert(String uPhone);
    User login(String uPhone);
    User findByUserId(Integer uid);
    void updatePersonPassword(User user);
    List<Score> selectScoreById(Integer uid);
    //通过手机号码预注册
    int signUpFirst(String telephoneNumber);
    //通过手机号码登录
    User signInByTelephoneNumber(String telephoneNumber);
    //通过手机号重置密码
    public int resetPassword(@Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);
    public int signUp(@Param(value = "username") String username, @Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);

    List<String> findAllPerms(String phone);

    List<User> findAllUserList(Integer page,Integer limit);

    void updateUserStatus(Integer uid, Integer status);

}
