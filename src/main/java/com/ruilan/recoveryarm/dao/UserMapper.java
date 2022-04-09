package com.ruilan.recoveryarm.dao;

import com.ruilan.recoveryarm.bean.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int register(User user);

    User queryUser(@Param("username") String username, @Param("password") String password);

    User findUserByName(@Param("username") String username);

    List<User> findAllUser();

    String findUserNameById(@Param("userId") int userId);

    int userCount();
}
