package com.sdkj.boot.service;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.Confirm;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.mapper.AdminMapper;
import com.sdkj.boot.mapper.ConfirmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfirmService {

    @Autowired
    ConfirmMapper confirmMapper;

    //确立关系
    public int AddConfirm(String Send_id,String Receive_id){
        int res = confirmMapper.AddConfirm(Send_id,Receive_id);
        return res;
    }

    public int QueryConfirm(String Send_id,String Receive_id){
        int res = confirmMapper.QueryConfirm(Send_id,Receive_id);
        return res;
    }

    public List<Confirm> compareuser(String userid){
        List<Confirm> confirms = confirmMapper.compareuser(userid);
        return confirms;
    }

    public User queryBySendId(String send_id){
        User user = confirmMapper.queryBySendId(send_id);
        return user;
    }

    //同意
    public int setstatus_agree(String send_id,String receive_id){
        int res = confirmMapper.setstatus_agree(send_id,receive_id);
        return res;
    }

    //拒绝
    public int setstatus_refuse(String send_id,String receive_id){
        int res = confirmMapper.setstatus_refuse(send_id,receive_id);
        return res;
    }

    public List<User> selectByRecId(String id){
        List<User> users = confirmMapper.selectByRecId(id);
        return  users;
    }

    public int agree(String receive_id,String username,String phone){
        int res = confirmMapper.agree(receive_id, username, phone);
        return res;
    }

    public int refuse(String receive_id,String username,String phone){
        int res = confirmMapper.refuse(receive_id,username,phone);
        return res;
    }


}
