package com.sdkj.boot.domain;

//好友
public class Friend {
    private int FriendId;
    private int UserId_F;
    private String FriendName;
    private String FriendPhone;

    public Friend(int friendId, int userId_F, String friendName, String friendPhone) {
        FriendId = friendId;
        UserId_F = userId_F;
        FriendName = friendName;
        FriendPhone = friendPhone;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "FriendId=" + FriendId +
                ", UserId_F=" + UserId_F +
                ", FriendName='" + FriendName + '\'' +
                ", FriendPhone='" + FriendPhone + '\'' +
                '}';
    }

    public int getFriendId() {
        return FriendId;
    }

    public void setFriendId(int friendId) {
        FriendId = friendId;
    }

    public int getUserId_F() {
        return UserId_F;
    }

    public void setUserId_F(int userId_F) {
        UserId_F = userId_F;
    }

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }

    public String getFriendPhone() {
        return FriendPhone;
    }

    public void setFriendPhone(String friendPhone) {
        FriendPhone = friendPhone;
    }
}
