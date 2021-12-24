package com.sdkj.boot.service;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin Login(String UserAccount,String Password){
        Admin admin = adminMapper.AdminLogin(UserAccount, Password);
        return admin;
    }
}
