package com.qf.examsys.service.impl;

import com.qf.examsys.dao.UserDao;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;


    @Override
    public void insert(User record) {
        userDao.insert(record);
    }

    @Override
    public String insert(String uPhone) {
        return userDao.insert(uPhone);
    }
}
