package com.sdkj.config;

import com.sdkj.User.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//配置类等同于配置文件
@Configuration      //告诉Spring这是一个配置类
@ComponentScan("com.sdkj")
public class MainConfig{

    //给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名作为id
    @Bean
    public User user(){
        return new User("张佳涛",12);
    }

    //在@Bean中作为组件的id 即 @Bean(name="id")
    @Bean(name="user02")
    public User beanname(){
        return new User("zhangjiatao",10);
    }

}

