package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.examsys.dao.UserDao;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;

    @Override
    public List<String> findAllPerms(String phone ) {
        return userDao.findAllPerms(phone);
    }

    @Override
    public List<User> findAllUserList(Integer page,Integer limit) {
        System.out.println(page+"sssss");
        System.out.println(limit+"aaaaa");
        PageHelper.startPage(page,limit);
        List<User> list = userDao.findAllUserList();

        return list;
    }

    @Override
    public void updateUserStatus(Integer uid, Integer status) {
        userDao.updateUserStatus(uid,status);
    }
}
