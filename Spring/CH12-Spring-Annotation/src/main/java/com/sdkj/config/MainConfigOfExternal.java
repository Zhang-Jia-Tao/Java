package com.sdkj.config;

import com.sdkj.Color.green;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



/*
*   BeanPostProcessor:bean的后置处理器 bean创建对象初始化前后进行拦截工作的
*   BeanFactoryPostProcessor:beanFactory的后置处理器 在BeanFactory标准初始化之后调用，所有的bean定义已经保存在
*       beanFactory中了,但bean的实例还未创建
*
* 流程分析
*   1）创建Ioc，Ioc创建对象
*   2）invokeBeanFactoryPostProcessors(beanFactory); 执行beanfactoryPostProcessor
*       如何找到所有的BeanPostProcessor并执行他们的方法？
*           1.直接在BeanFactory中找到所有类型是BeanFactoryPostProcessor，并执行其方法
*
*
* public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor{
*
*   //在所有bean定义信息将要被加载，bean实例还未创建时执行
*   void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;
*   }
*   BeanDefinitionRegistryPostProcessor优先于BeanFactoryPostProcessor执行，利用 BeanDefinitionRegistryPostProcessor
*       给容器中额外增加组件
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
