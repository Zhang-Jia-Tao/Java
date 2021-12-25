package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.Confirm;
import com.sdkj.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConfirmMapper {

    //发送方申请添加关系
    public int AddConfirm(String Send_id,String Receive_id);

    //查询添加信息，看是否已经添加过了
    public int QueryConfirm(String Send_id,String Receive_id);

    public List<Confirm> compareuser(String userid);

    public User queryBySendId(String send_id);

    public int setstatus_agree(String send_id,String receive_id);

    public int setstatus_refuse(String send_id,String receive_id);

    public List<User> selectByRecId(String id);

    public int agree(String receive_id,String username,String phone);

    public int refuse(String receive_id,String usernaem,String phone);

}
