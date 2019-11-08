package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.examsys.dao.UserDao;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.dao.StatisticsDao;
import com.qf.examsys.dao.UserDao;
import com.qf.examsys.entity.Score;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;
    @Autowired(required = false)
    StatisticsDao statisticsDao;

    @Override
    public User findUserByPhone(String phone) {
        return userDao.findByPhone(phone);
    }


    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public int insert(String uPhone) {
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
    public List<Score> selectScoreById(Integer uid) {
        return statisticsDao.listPersonalScore(null,null,uid);
    }

    @Override
    public int signUpFirst(String telephoneNumber) {
        return userDao.signUpFirst(telephoneNumber);
    }

    @Override
    public User signInByTelephoneNumber(String telephoneNumber) {
        return userDao.signInByTelephoneNumber(telephoneNumber);
    }

    @Override
    public int resetPassword(String password, String telephoneNumber) {
        return userDao.resetPassword(password,telephoneNumber);
    }

    @Override
    public int signUp(String username, String password, String telephoneNumber) {
        return userDao.signUp(username,password,telephoneNumber);
    }



    @Override
    public List<String> findAllPerms(String phone ) {
        return userDao.findAllPerms(phone);
    }

    @Override
    public List<User> findAllUserList(Integer page,Integer limit) {
      /*  System.out.println(page+"sssss");
        System.out.println(limit+"aaaaa");*/
        PageHelper.startPage(page,limit);
        List<User> list = userDao.findAllUserList();

        return list;
    }

    @Override
    public void updateUserStatus(Integer uid, Integer status) {
        userDao.updateUserStatus(uid,status);
    }
}
