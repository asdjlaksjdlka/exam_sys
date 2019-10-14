package com.qf.examsys.dao;

import com.qf.examsys.entity.User;

public interface UserDao {
    //注册
    Integer insert(User record);
    Integer insertSelective(User record);
    String insert(String uPhone);
}
