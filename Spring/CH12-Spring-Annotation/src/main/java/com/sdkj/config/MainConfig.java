package com.sdkj.config;

import com.sdkj.User.Student;
import com.sdkj.User.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/*
* @ComponentScan的作用
*   相当于xml配置文件当中的组件扫描器
*   会扫描指定路径下的所有文件，当类上有@Service、@Controller、@Repository、@Component这四个
*   注解的其中之一，都会自动创建该类的组件于容器之中
* */

//配置类等同于配置文件
@Configuration      //告诉Spring这是一个配置类
@ComponentScan("com.sdkj.controller")   //指定要扫描的包
public class MainConfig{

    //给容器中注册一个Bean，类型为返回值的类型，id默认是用方法名作为id
    @Bean
    public User user(){
        return new User("张佳涛",12);
    }

    //在@Bean(name="***")中***作为组件的id 即 @Bean(name="id")
    @Bean(name="user02")
    public User beanname(){
        return new User("zhangjiatao",10);
    }

    @Bean(name="stu")
    public Student student(){
        return new Student();
    }

}

