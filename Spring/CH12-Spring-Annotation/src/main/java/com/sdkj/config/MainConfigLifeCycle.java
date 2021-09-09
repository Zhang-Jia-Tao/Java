package com.sdkj.config;
import com.sdkj.User.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/*
* bean的生命周期：
*   bean的创建----bean的初始化-----bean的销毁
*
* bean的创建
*   单实例：在IOC容器启动时创建bean
*   多实例：在每次获取bean时创建
*
* 初始化：
*   对象创建完成，并赋值，随后调用初始化方法
*
* 销毁：
*   单实例：容器关闭时，进行销毁
*   多实例：容器不会管理这个bean，容器不会调用销毁方法
*
* 容器管理bean的生命周期
*   可以自定义初始化和销毁方法，容器在bean进行到相应的生命周期的阶段时，会调用自定义的初始化和销毁方法
*   自定义初始化和销毁方法的创建方式：   共有四种
*       1）指定初始化和销毁方法
*               在配置文件中(beans.xml)<bean.....init-method="***",destory-method="***">
*               在注解类中，通过使用@Bean(initMethod="***",destoryMethod="***")
*                   ***： 所写内容为要创建的类中有关于初始化和销毁的相关方法名
*
*       2）通过让Bean实现InitializingBean(定义初始化逻辑)、实现DisposableBean(定义销毁逻辑)
*
*       3) 通过使用JSR250：
*               @PostConstruct：在Bean创建完成并属性赋值完成后执行初始化方法
*               @PreDestroy： 在容器销毁Bean之前，通知进行清理工作
*
*       4）BeanPostProcessor[interface], bean后置处理器
*           作用：在bean初始化前后进行一些处理工作
*           要实现的两个方法：
*               1.postProcessBeforeInitialization:在初始化之前进行工作
*               2.postProcessAfterInitialization:在初始化之后进行工作
*
* */
@ComponentScan("com.sdkj.User")
@Configuration
public class MainConfigLifeCycle {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Student student01(){
        return new Student();
    }

}
