package com.sdkj.boot.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//管理员
@ToString
@Getter
@Setter
public class Admin {
    private int AdminId;
    private String UserAccount;
    private String Password;

    //管理员等级
    private int AdminLeve;



}
