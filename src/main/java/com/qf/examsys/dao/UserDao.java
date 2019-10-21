package com.qf.examsys.dao;

import com.qf.examsys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.qf.examsys.entity.User;
import org.apache.ibatis.annotations.Param;


public interface UserDao {


    List<String> findAllPerms(String phone);

    List<User> findAllUserList();

    void updateUserStatus(@Param("uid") Integer uid,@Param("status")Integer status);

    User findUserById1(Integer uid);
    //注册
    Integer insert(User user);
    Integer insertSelective(User user);
    //手机预注册
    int insert(String uPhone);
    //登录
    User login(String uPhone);
    //通过id查看个人用户信息
    User findByUserId(Integer uid);
    //重置密码
    void updatePersonPassword(User user);
    //通过手机号码预注册
    public int signUpFirst(String telephoneNumber);
    //通过手机号码登录
    public User signInByTelephoneNumber(String telephoneNumber);
    // 通过手机号码重置密码
    public int resetPassword(@Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);
    //补充用户信息
    public int signUp(@Param(value = "username") String username, @Param(value = "password") String password, @Param(value = "telephoneNumber") String telephoneNumber);


}
