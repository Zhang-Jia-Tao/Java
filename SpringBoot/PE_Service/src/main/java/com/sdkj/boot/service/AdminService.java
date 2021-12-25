package com.sdkj.boot.service;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin Login(String UserAccount,String Password){
        Admin admin = adminMapper.AdminLogin(UserAccount, Password);
        return admin;
    }

    //查询全部管理员
    public List<Admin> selectmis(){
        return adminMapper.selectmis();
    }

    public int DeleteById(String id){
        return adminMapper.DeleteById(id);
    }

    public int add(String name,String password,String level){
        return adminMapper.add(name, password, level);
    }

    public Admin SelectById(String id){
        return adminMapper.SelectById(id);
    }

    public int updateById(String id,String name,String password,String level){
        return adminMapper.updateById(name,password,level,id);
    }
}
