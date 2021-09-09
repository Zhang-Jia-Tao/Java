package com.sdkj.service;

import com.sdkj.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //自动注入

    //@Qualifier("userDao02")   添加@Qualifier后，test方法会返回lable等于2
    @Autowired    //@Autowired(required="false")  若没有匹配成功，也不会报错。 默认为true
    UserDao userDao;

    public UserService(){
    }

    public void Print(){
        System.out.println(userDao);
    }

    @Autowired
    public void dosome(UserDao userDao){
        System.out.println("=========dosome========");
        System.out.println(userDao);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userDao=" + userDao +
                '}';
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
