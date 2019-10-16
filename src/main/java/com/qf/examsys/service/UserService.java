package com.qf.examsys.service;

import com.qf.examsys.entity.User;

public interface UserService {
    void insert(User user);
    Integer insert(String uPhone);
    User login(String uPhone);
    User findByUserId(Integer uid);
    void updatePersonPassword(User user);
    User selectScoreById(Integer uid);
}
