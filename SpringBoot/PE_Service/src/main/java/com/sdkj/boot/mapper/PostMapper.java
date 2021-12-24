package com.sdkj.boot.mapper;

import com.sdkj.boot.domain.Post;
import com.sdkj.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostMapper {

    //根据帖子的类型查询帖子
    List<Post> QueryPost(String type);

    //发表的帖子 存储至mysql
    int InsertPost(int User_Id,String Content,int NumberOfLikes,int NumberOfConnection,String type,String UserName);

    //增加点赞数
    int UpdateLike(String PostId);
    int QueryPostLike(String PostId);

    //查询帖子
    List<Post> SearchPost();

    //根据id，查询帖子内容
    String QueryContent(String id);

    //根据帖子的id，进行删除操作
    Integer DeleteById(String id);

    //根据帖子id，进行封号处理
    Integer UpdateById(String id);

    //根据PostID，查询UserId，然后再进行查询用户信息
    User QueryUser(String id);

    List<Post> SelectById(String id);

}
