package com.beanFactory;

import com.sdkj.Color.green;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    //BeanDefinitionRegistry bean定义信息的保存中心，以后BeanFactory就按照BeanDefinitionRegistry里面
    //  保存的每一个bean的定义信息创建bean实例
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("postProcessorBeanDefinitionRegistry().....");
        //在这里可以添加bean实例
//      RootBeanDefinition definition = new RootBeanDefinition(Blue.class); ----- 方法一
//      AbstractBeanDefinition definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(green.class);
//        registry.registerBeanDefinition("green",);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessorBeanFactory()....");
    }
}
