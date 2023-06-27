package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAuth;
import com.example.demo.model.Message;

import java.util.List;


public interface UserAuthService {


    Message checkPasswordById(Long userId, String password);

    void addUser(UserAuth newUserAuth);
}
