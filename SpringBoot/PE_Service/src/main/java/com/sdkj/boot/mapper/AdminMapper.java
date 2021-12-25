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

    public List<Admin> selectmis();

    //删除管理员
    public int DeleteById(String id);

    //添加管理员
    public int add(String name,String password,String level);

    //通过id查询admin
    public Admin SelectById(String id);

    public int updateById(String username,String password,String level,String id);
}
