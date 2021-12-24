package com.sdkj.boot.service;

import com.sdkj.boot.domain.User;
import com.sdkj.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> QueryUser(){
        List<User> users = userMapper.QueryUser();
        return users;
    }

    //用于用户登陆
    public User UserLogin(String username,String password){
        User user = userMapper.UserLogin(username,password);
        return user;
    }

    public int UpdateById(String username,String password,String sex,String age,String pe_dir,
                          String phone,String userid){
        int res = userMapper.UpdateById(username,password,sex,age,pe_dir,phone,userid);
        return res;
    }

    //用于用户注册
    public int UserRegister(String username,String password,String sex,String age,String dir,String phone){
        int i = userMapper.UserRegister(username, password, sex, age, dir,phone);
        return i;
    }

    //添加IP
    public int UpdateIP(String IP,String username){
        int i = userMapper.UpdateIP(username,IP);
        return i;
    }

    //添加好友
    public List<User> SelectFriend(String id){
        List<User> users = userMapper.SelectFriend(id);
        return users;
    }

}
