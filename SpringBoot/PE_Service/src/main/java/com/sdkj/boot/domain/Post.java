package com.sdkj.boot.domain;

import java.net.Inet4Address;

//帖子
public class Post {
    private int PostId;
    private int UserId;
    private String Content;
    private Integer NumberOfLikes;
    private Integer NumberOfConnection;
    private String UserName;


    public Post(int postId, int userId, String content, Integer numberOfLikes, Integer numberOfConnection,String UserName) {
        PostId = postId;
        UserId = userId;
        Content = content;
        NumberOfLikes = numberOfLikes;
        NumberOfConnection = numberOfConnection;
        UserName = UserName;
    }

    @Override
    public String toString() {
        return "Post{" +
                "PostId=" + PostId +
                ", UserId=" + UserId +
                ", Content='" + Content + '\'' +
                ", NumberOfLikes=" + NumberOfLikes +
                ", NumberOfConnection=" + NumberOfConnection +
                ", UserName='" + UserName + '\'' +
                '}';
    }

    public int getPostId() {
        return PostId;
    }

    public void setPostId(int postId) {
        PostId = postId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getNumberOfLikes() {
        return NumberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        NumberOfLikes = numberOfLikes;
    }

    public Integer getNumberOfConnection() {
        return NumberOfConnection;
    }

    public void setNumberOfConnection(int numberOfConnection) {
        NumberOfConnection = numberOfConnection;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
