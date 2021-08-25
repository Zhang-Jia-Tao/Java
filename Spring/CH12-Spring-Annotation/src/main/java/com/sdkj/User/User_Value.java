package com.sdkj.User;

import org.springframework.beans.factory.annotation.Value;

public class User_Value {


    //给属性赋值
    /*  @Value
    *     给属性赋值
    *       1.基本数值
    *       2.可以写SpEL，#{}
    *       3.可以写${},取出配置文件中的值 【配置文件中的key、value键值对会保存在运行环境变量中】
    * */

    @Value("zhangjiatao")
    private String name;

    @Value("18")
    private Integer age;

    @Value("${person.nickname}")
    private String nickname;

    @Override
    public String toString() {
        return "User_Value{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
