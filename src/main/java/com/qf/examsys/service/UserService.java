package com.qf.examsys.service;

import com.github.pagehelper.PageInfo;
import com.qf.examsys.entity.User;

import java.util.List;

public interface UserService {

    List<String> findAllPerms(String phone);

    PageInfo<User> findAllUserList(Integer page,Integer limit);

    void updateUserStatus(Integer uid, Integer status);

}
