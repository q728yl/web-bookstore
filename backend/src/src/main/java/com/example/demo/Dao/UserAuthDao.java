package com.example.demo.Dao;

import com.example.demo.entity.UserAuth;

public interface UserAuthDao {
//    UserAuth getUserAuthByUsernameAndPassword(String username,String password);

    String getPasswordById(Long userId);

    void addUser(UserAuth newUserAuth);
}
