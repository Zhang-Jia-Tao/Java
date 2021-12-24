package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    public int query(String username,String phone);


    public int insert(String content,String send_id,String receive_id,String send_time);

    public List<Message> SelectMessage(String send_id,String receive_id);
}
