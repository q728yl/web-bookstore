package com.example.demo.service;

import com.example.demo.Dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public Message getAllUsers(){
        List<User> userAuth = userDao.getAllUsers();
        if(userAuth!= null){
            return new Message("登陆成功",true,userAuth);
        }else{
            return new Message("密码错误",false,null);
        }
    }
    @Override
    public List<User> getUserById(Long userId){
        return  userDao.getUserById(userId);

    }
    @Override
    public  Message saveReviewComment(Long userId, String comments){
        userDao.saveReviewComment(userId,comments);
//        if(userAuth!= null){
            return new Message("审核意见已修改",true,null);
//        }else{
//            return new Message("审核意见修改失败",false,null);
//        }
    }
    @Override
    public  Message findUserByUser_type(Long user_type){
        List<User> userAuth = userDao.findUserByUser_type(user_type);
        if(userAuth!= null){
            return new Message("登陆成功",true,userAuth);
        }else{
            return new Message("密码错误",false,null);
        }
    }
    @Override
    public Message getUserId(String username) {
        User user = userDao.getId(username);
        if (user != null) {
            return new Message("用户存在", true, user);
        } else {
            return new Message("用户不存在", false, null);
        }
    }
    @Override
    public Message ban(Long userId){
        userDao.ban(userId);
        return new Message("更改成功",true,null);
    }
    @Override
    public Message noBan(Long userId){
        userDao.noBan(userId);
        return new Message("更改成功",true,null);
    }
    @Override
    public  User addUser(String username, String email, String address, Long userType, String comments, Long status){
        return userDao.addUser(username,email,address,userType,comments,status);
    }
}
