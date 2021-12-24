package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FriendMapper {

    //查询好友
    public List<Friend> QueryFriend(int UserId);


    public int UpdateFriend(int UserId,String FriendName,String FriendPhone);

}
