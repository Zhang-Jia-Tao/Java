package com.sdkj.config;


import com.sdkj.User.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig02 {

    /*
    * @Scope:  可以使用在方法上或者类上
    *   prototype:多实例的   ioc容器启动并不会调用方法创建对象放在容器中
    *                       每次获取的时候才会调用方法创建对象
    *   singleton：单实例的(默认)   ioc容器启动时会调用方法创建对象放在容器中
    *                            以后每次获取就是直接从容器中取
    *
    * */
    @Scope(value="prototype")
    @Bean
    public User user(){
        System.out.println("user对象创建中");
        return new User("我还年轻，吃苦趁现在",2021);
    }


    /*
    *@Lazy:懒加载
    *   应用在单实例bean的情况下
    *   单实例bean：默认在容器启动的时候创建对象
    *   添加懒加载后，容器启动时不会创建对象。第一次使用(获取)bean时，才会创建对象并初始化于容器之中
    * */
    @Lazy
    @Bean
    public User user03(){
        System.out.println("user03正在创建中");
        return new User("我还年轻，吃苦趁现在",2021);
    }

}
