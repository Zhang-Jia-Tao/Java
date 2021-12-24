package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Admin;
import com.sdkj.boot.domain.Message;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.service.AdminService;
import com.sdkj.boot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;


    //返回接收方的id
    @RequestMapping("/query")
    public ResultInfo query(@RequestParam("username")String username,
                            @RequestParam("phone")String phone){
        int i = service.query(username, phone);
        return new ResultInfo(true,i,"victory");
    }


    @RequestMapping("/add")
    public ResultInfo insert(@RequestParam("content")String content,
                             @RequestParam("receive_id")String receive_id,
                             HttpSession session){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String currnt_time = df.format(new Date());// new Date()为获取当前系统时间
        User user = (User)session.getAttribute("user");
        String send_id = user.getUserId()+"";
        int i = service.insert(content, send_id, receive_id, currnt_time);
        if(i != 1){
            return new ResultInfo(false,null,"loose");
        }
        return new ResultInfo(true,null,"victory");
    }

    @RequestMapping("/search")
    public ResultInfo search(@RequestParam("receive_id")String receive_id,HttpSession session){
        User user = (User) session.getAttribute("user");
        String send_id = user.getUserId()+"";
        List<Message> messages = service.SelectMessage(send_id, receive_id);
        if(messages != null){
            return new ResultInfo(true,messages,"ok,down");
        } else {
            return new ResultInfo(false,null,"false");
        }
    }

}
