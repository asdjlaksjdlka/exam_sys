package com.qf.examsys.service.impl;

import com.qf.examsys.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    UserDao userDao;
}
