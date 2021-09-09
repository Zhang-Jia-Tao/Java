package com.sdkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Scope(value = "prototype")
@Controller
public class MyWeb {

    //@Autowired 用在构造方法上

    public String website="www.baidu.com";

//    @Autowired
    public MyWeb(String website){
        this.website = website;
    }

    @Override
    public String toString() {
        return "MyWeb{" +
                "website='" + website + '\'' +
                '}';
    }
}
