package com.sdkj.config;

import com.sdkj.Color.green;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



/*
*   BeanPostProcessor:bean的后置处理器 bean创建对象初始化前后进行拦截工作的
*   BeanFactoryPostProcessor:beanFactory的后置处理器 在BeanFactory标准初始化之后调用，所有的bean定义已经保存在
*       beanFactory中了
*
* */
@ComponentScan({"com.beanFactory"})
@Configuration
public class MainConfigOfExternal {

    @Bean
    public green green(){
        return new green();
    }

}
