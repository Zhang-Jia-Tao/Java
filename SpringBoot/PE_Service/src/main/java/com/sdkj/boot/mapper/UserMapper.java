package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    public List<User> QueryUser();

    /*
    * update User set UserName = #{username},Password = #{password},Sex = #{sex},Age = #{age},Pe_Dir = #{pe_dir},
                        Phone = #{phone} where UserId = #{userid}
    * */
    public int UpdateById(String username,String password,String sex,String age,String pe_dir,
                          String phone,String userid);


    //用于用户登陆的
    public User UserLogin(String username,String password);

    //用于用户注册
    int UserRegister(String username,String password,String sex,String age,String dir,String phone);

    //更新用户信息


    //用于添加IP
    int UpdateIP(String username,String IP);

    //用于添加好友
    public List<User> SelectFriend(String id);



}
