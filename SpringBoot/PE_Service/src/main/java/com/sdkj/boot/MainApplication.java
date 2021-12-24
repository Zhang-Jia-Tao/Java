package com.sdkj.boot;



import com.sdkj.boot.config.MyConfig;

import com.sdkj.boot.service.DataService;
import com.sdkj.boot.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
* 主程序类
* @SpringBootApplication:表明这是一个SpringBoot应用
*   SpringBoot的核心注解，主要用于开启Spring自动配置
* */
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {

        //返回IOC容器
        ConfigurableApplicationContext cfb = SpringApplication.run(MainApplication.class,args);

        //从容器中获取组件
        MyConfig myConfig = cfb.getBean(MyConfig.class);

        DataService bean = cfb.getBean(DataService.class);
        System.out.println(bean.QueryData("计算机"));
        UserService userService = cfb.getBean(UserService.class);
        System.out.println(userService.QueryUser());



    }
}
