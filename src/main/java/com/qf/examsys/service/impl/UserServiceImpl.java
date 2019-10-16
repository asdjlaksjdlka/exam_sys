package com.qf.examsys.service.impl;

import com.qf.examsys.common.JsonReasult;
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
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public Integer insert(String uPhone) {
        return userDao.insert(uPhone);
    }

    @Override
    public User login(String uPhone) {
        return userDao.login(uPhone);
    }

    @Override
    public User findByUserId(Integer uid) {
        return userDao.findByUserId(uid);
    }

    @Override
    public void updatePersonPassword(User user) {
        userDao.updatePersonPassword(user);
    }

    @Override
    public User selectScoreById(Integer uid) {
        return userDao.selectScoreById(uid);


    }
}
