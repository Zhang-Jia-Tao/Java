package com.sdkj.config;


import com.sdkj.User.User_Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


//@PropertySource:读取外部配置文件中的key、value键值对，保存到运行的环境变量中

@PropertySource("classpath:/person.properties")
@Configuration   //表明此类为配置类
public class MainConfigPropertyValue {

    @Bean
    public User_Value userValue(){
        return new User_Value();
    }

}
