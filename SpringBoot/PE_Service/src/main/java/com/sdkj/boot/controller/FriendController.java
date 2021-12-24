package com.sdkj.boot.controller;

import com.sdkj.boot.domain.Friend;
import com.sdkj.boot.domain.ResultInfo;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @RequestMapping("/query")
    public ResultInfo QueryFriend(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Friend> friends = friendService.QueryFriend(user.getUserId());
        if(friends!=null){
            return new ResultInfo(true,friends,"好友列表传送成功");
        } else {
            return new ResultInfo(true,null,"该用户没有好友列表");
        }

    }

    @RequestMapping("/insert")
    public ResultInfo UpdateFriend(HttpSession session,@RequestParam("name")String username,
                                   @RequestParam("phone") String phone){
        User user = (User) session.getAttribute("user");
        int res = friendService.UpdateFriend(user.getUserId(),username,phone);
        if(res == 1){
            return new ResultInfo(true,null,"添加好友成功");
        } else {
            return new ResultInfo(false,null,"添加好友失败");
        }
    }
}
