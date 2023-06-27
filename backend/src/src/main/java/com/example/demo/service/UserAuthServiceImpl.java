package com.example.demo.service;

import com.example.demo.Dao.UserAuthDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.entity.UserAuth;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserAuthDao userAuthDao;
    @Override
    public Message checkPasswordById(Long userId, String password){
        String p = userAuthDao.getPasswordById(userId);
        if(Objects.equals(p, password)){
                return new Message("登陆成功",true,null);
            }else{
                return new Message("密码错误",false,null);
            }

    }
    @Override
    public void addUser(UserAuth newUserAuth){
        userAuthDao.addUser(newUserAuth);
    }
}
