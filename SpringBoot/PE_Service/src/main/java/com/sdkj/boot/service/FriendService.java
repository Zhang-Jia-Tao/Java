package com.sdkj.boot.service;

import com.sdkj.boot.domain.Friend;
import com.sdkj.boot.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendMapper friendMapper;

    public List<Friend> QueryFriend(int UserId){
        List<Friend> friends = friendMapper.QueryFriend(UserId);
        return friends;
    }

    public int UpdateFriend(int UserId,String FriendName,String FriendPhone){
        int res = friendMapper.UpdateFriend(UserId,FriendName,FriendPhone);
        return res;
    }
}
