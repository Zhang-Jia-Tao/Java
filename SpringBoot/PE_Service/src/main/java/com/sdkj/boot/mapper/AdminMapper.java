package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    //用于管理员登陆
    public Admin AdminLogin(String UserAccount,String Password);

}
