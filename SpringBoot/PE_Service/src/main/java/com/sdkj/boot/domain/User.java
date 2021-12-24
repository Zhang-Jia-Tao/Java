package com.sdkj.boot.domain;


//User ： 用户表
public class User {
    private int UserId;
    private String UserName;
    private String Password;
    private String Sex;
    private int Age;
    private String PE_Dir;
    private String Phone;
    private String IP;


    public User(int userId, String userName, String password, String sex, int age, String PE_Dir,String Phone,String IP) {
        UserId = userId;
        UserName = userName;
        Password = password;
        Sex = sex;
        Age = age;
        this.PE_Dir = PE_Dir;
        this.Phone = Phone;
        this.IP = IP;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Age=" + Age +
                ", PE_Dir='" + PE_Dir + '\'' +
                ", Phone='" + Phone + '\'' +
                ", IP='" + IP + '\'' +
                '}';
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getPE_Dir() {
        return PE_Dir;
    }

    public void setPE_Dir(String PE_Dir) {
        this.PE_Dir = PE_Dir;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
