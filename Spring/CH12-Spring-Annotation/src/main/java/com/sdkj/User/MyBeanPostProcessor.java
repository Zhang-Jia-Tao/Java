package com.sdkj.User;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    //4）BeanPostProcessor[interface], bean后置处理器
    //*           作用：在bean初始化前后进行一些处理工作
    //*           要实现的两个方法：
    //*               1.postProcessBeforeInitialization:在初始化之前进行工作
    //*               2.postProcessAfterInitialization:在初始化之后进行工作
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before:current bean is"+beanName+"===>"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After:current bean is"+beanName+"===>"+bean);
        return bean;
    }
}
