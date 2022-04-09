package com.ruilan.recoveryarm.service;

import com.ruilan.recoveryarm.bean.User;

import java.util.List;

public interface UserService {
    int register(User user);

    User queryUser(String username, String password);

    User findUserByName(String username);

    List<User> findAllUser();

    int userCount();

    List<List> weekRate();
}