package com.sdkj.boot.service;

import com.sdkj.boot.domain.Post;
import com.sdkj.boot.domain.User;
import com.sdkj.boot.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;


    public List<Post> QueryPost(String type){
        List<Post> posts = postMapper.QueryPost(type);
        return posts;
    }

    //将发表的帖子存至mysql
    public int InsertPost(int User_Id,String Content,int NumberOfLikes,int NumberOfConnection,String type,String UserName){
        int res = postMapper.InsertPost(User_Id,Content,NumberOfLikes,NumberOfConnection,type,UserName);
        return res;
    }

    //增加点赞
    public int UpdateLike(String PostId){
        int res = postMapper.UpdateLike(PostId);
        return res;
    }

    public int UpdatePostLike(String PostId){
        Integer res = postMapper.QueryPostLike(PostId);
        return res;
    }

    //查询帖子
    public List<Post> SearchPost(){
        List<Post> list = postMapper.SearchPost();
        return list;
    }


    //查询帖子内容
    public String QueryContent(String id){
        String res = postMapper.QueryContent(id);
        return res;
    }

    //根据id，进行删帖操作
    public Integer DeleteById(String id){
        Integer res = postMapper.DeleteById(id);
        return res;
    }

    //根据id，进行封号处理
    public Integer UpdateById(String id){
        Integer res = postMapper.UpdateById(id);
        return res;
    }


    //查询用户信息
    public User QueryUser(String id){
        User user = postMapper.QueryUser(id);
        return user;
    }

    public List<Post> SelectById(String id){
        List<Post> posts = postMapper.SelectById(id);
        return posts;
    }

}
