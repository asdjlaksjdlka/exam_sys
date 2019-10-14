package com.qf.examsys.service;

import com.qf.examsys.entity.User;

public interface UserService {
    void insert(User record);
    String insert(String uPhone);
}
