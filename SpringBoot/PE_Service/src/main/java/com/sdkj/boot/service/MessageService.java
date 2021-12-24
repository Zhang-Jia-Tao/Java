package com.sdkj.boot.service;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.Message;
import com.sdkj.boot.mapper.AdminMapper;
import com.sdkj.boot.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public int query(String username,String phone){
        int query = messageMapper.query(username, phone);
        return query;
    }

    public int insert(String content,String send_id,String receive_id,String send_time){
        int insert = messageMapper.insert(content, send_id, receive_id, send_time);
        return insert;
    }

    public List<Message> SelectMessage(String send_id,String receive_id){
        List<Message> messages = messageMapper.SelectMessage(send_id, receive_id);
        return messages;
    }
}
