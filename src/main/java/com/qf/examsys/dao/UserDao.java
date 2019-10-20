package com.qf.examsys.dao;

import com.qf.examsys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {


    List<String> findAllPerms(String phone);

    List<User> findAllUserList();

    void updateUserStatus(@Param("uid") Integer uid,@Param("status")Integer status);

    User findUserById1(Integer uid);
}
