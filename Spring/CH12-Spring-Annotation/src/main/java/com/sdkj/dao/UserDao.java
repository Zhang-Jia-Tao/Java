package com.sdkj.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    //这个label是为了区别不同的bean而设置的，无其他特殊含义
    private String label = "1";

    public UserDao(){}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "UserDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
