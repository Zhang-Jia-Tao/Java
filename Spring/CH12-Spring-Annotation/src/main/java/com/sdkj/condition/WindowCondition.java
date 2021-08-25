package com.sdkj.condition;


import com.sdkj.config.MainConfig03;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;



public class WindowCondition implements Condition {

    /*
    * ConditionContext:判断条件能使用的上下文
    * AnnotatedTypeMetadata：注释信息
    * */

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //能获取到ioc的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();

        //能够获取到类加载器
        ClassLoader classLoader = conditionContext.getClassLoader();

        //能获取到bean定义的注册类
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        //能够获取到当前环境信息
        Environment environment = conditionContext.getEnvironment();


        String property = environment.getProperty("os.name");
        if(property.contains("Window")){
            return true;
        }
        return false;
    }
}
