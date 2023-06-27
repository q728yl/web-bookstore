package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAuth;
import com.example.demo.model.Message;

import com.example.demo.service.UserAuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {


@Autowired
private UserService userService;
    @Autowired
    private UserAuthService userAuthService;
  @Autowired
  private com.example.demo.repository.UserRepository userRepository;
    @GetMapping("/getUserList")
    public Message getUserList(){
       Message m  = userService.getAllUsers();
        List<User> userList = (List<User>) m.getData();
        if(userList!=null)
            return new Message("获取用户信息成功",true,userList);
        else
            return new Message("获取用户信息失败",false,null);
    }
    @GetMapping("/getUserById")
    public Message getUserById(@RequestParam("userId") Long userId){
        System.out.println(userId);
        List<User> userList = userService.getUserById(userId);
        User user = null;
        if(userList!=null){
            user = userList.get(0);
        }else{
            return new Message("获取用户信息失败",false,null);
        }
        return new Message("获取用户信息成功",true,user);
    }

    @GetMapping("/getUserListByUserType")
    public Message getUserListByUserType(@RequestParam("userType") Long userType){
        Message m  = userService.findUserByUser_type(userType);
        List<User> userList = (List<User>) m.getData();
        if(userList!=null)
            return new Message("获取用户信息成功",true,userList);
        else
            return new Message("获取用户信息失败",false,null);
    }
    @PostMapping("/login")
    public Message checkPassword(@RequestBody Map<String,Object> body){
        System.out.println(body.get("password"));
        String username =(String) body.get("username");
        String password =(String) body.get("password");
        Message m = userService.getUserId(username);
        User user = null;
        Long userId = 0L ;
        if(m.getOk()){
            user = (User) m.getData();
            userId = user.getId();
        }else return m;
        if(((User) m.getData()).getStatus() == 0)
            return new Message("用户已被禁用",false,null);
        Message message = userAuthService.checkPasswordById(userId,password);
        if(message.getOk()){
            return new Message("登陆成功",true,user);
        } else
            return new Message("登陆失败",false,null);
    }
    @PostMapping("/saveReviewComment")
    public Message saveReviewComment(@RequestBody Map<String,Object> body){
       // System.out.println(body.get("password"));
        Long userId = Long.valueOf(body.get("userId").toString());
        String comments =(String) body.get("comment");
        Message m = userService.saveReviewComment(userId,comments);
        return m;
    }
    @PostMapping("/banUsers")
    public Message manageUsers(@RequestBody Map<String,Object> body){
        Long userId = Long.valueOf(body.get("userId").toString());
        Message m = userService.ban(userId);
        return m;
    }
    @PostMapping("/noBanUsers")
    public Message noBan(@RequestBody Map<String,Object> body){
        Long userId = Long.valueOf(body.get("userId").toString());
        Message m = userService.noBan(userId);
        return m;
    }

    @PostMapping("/register")
    public Message registerUser(@RequestBody Map<String,Object>body) {
        // 获取用户数据并执行注册逻辑
        String username = (String) body.get("username");
        User user = userRepository.findUserByUsername(username);
        if (user != null)
            return new Message("已存在同名用户",false,null);

        String password = (String) body.get("password");
        String email = (String) body.get("email");
        String address = (String) body.get("address");
        String comments = "无";
        Long userType = 1L;
        Long status = 1L;
        // 在这里执行你的注册逻辑，例如将用户数据保存到数据库
        User newUser = userService.addUser(username,email,address,userType,comments,status);
        // 返回注册成功的响应
        System.out.println(newUser);
        UserAuth newUserAuth = new UserAuth();
        System.out.println(newUser.getId());
//        newUserAuth.setUserId(newUser.getId());
        newUserAuth.setPassword(password);
        userAuthService.addUser(newUserAuth);
        return new Message("注册成功",true,newUser);
    }

}
