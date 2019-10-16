package com.qf.examsys.dao;

import com.qf.examsys.entity.User;

public interface UserDao {
    //注册
    Integer insert(User user);
    Integer insertSelective(User user);
    //手机预注册
    Integer insert(String uPhone);
    //登录
    User login(String uPhone);
    //通过id查看个人用户信息
    User findByUserId(Integer uid);
    //重置密码
    void updatePersonPassword(User user);
    //根据id查看个人成绩
    User selectScoreById(Integer uid);

}
