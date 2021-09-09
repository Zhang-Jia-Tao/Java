package com.sdkj.config;

import com.sdkj.dao.UserDao;
import com.sdkj.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
* @Autowired: 自动注入
*   1）默认优先按照类型去容器中寻找对应的组件
*   2）如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中匹配
*   3）@Qualifier("name")：使用@Qualifier指定需要装配的组件的id，而不再需要使用属性名
*   4）自动装配默认一定要将属性与容器中的组件相匹配，没有匹配成功，会报错
*       可以使用@Autowried(required="false") 若没有匹配成功，也不会报错。
*
*   5）@Primary：用在@Bean注解的方法上面，表示此个要创建的组件为首选的bean(就是当有多个同类型的bean时，会优先选择它来进行自动注入)
*           使用@Primary的同时也可以继续使用@Qualifier指定需要自动注入的bean的id
*
*
* Spring还支持@Resource、@Inject  【java规范中的注解】
*   @Resource：
*       与@Autowired都具有自动装配的功能；默认是按照组件的名称(bean的id)从容器中寻找
*       不支持@Primary
*   @Inject：
*       需要导入javax.inject包，也具有自动装配的功能 支持@Primary
*
* 当@Autowired标注在方法上面，Spring容器创建当前对象，就会调用方法，完成赋值
*   方法使用的参数，自定义类型的参数，会从ioc容器中寻找
*
* 当@Autowired标注在构造方法上面，如果组件中只有一个有参构造方法，这个构造方法的@Autowired可以省略
*   参数位置的组件还是可以自动获取
*
* @Bean标注的方法创建对象的时候，方法参数的值从容器中获取 默认可以不用写@Autowired 都能自动装配
*
*
* 自定义组件想要使用Spring容器底层的一些组件(ApplicationContext、BeanFactory、XXX)
*       自定义的组件要实现***Aware；在创建对象的时候，会调用接口规定的方法调用注入相关组件
*       把Spring底层一些组件注入到自定义的Bean中
*       ***Aware：功能使用***Processor
*           例如ApplicationContextAware===》ApplicationContextAwareProcessor
*
*
* ==================================
* @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定的话，任何环境下都能注册这个组件
*   1）加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中，默认是default环境
*   2）写在配置类上，只有是指定的环境的时候，整个配置类类面的所有配置才能开始生效
*   3）没有标注环境标识的bean，在任何环境下都是加载的
* */

@ComponentScan({"com.sdkj.dao","com.sdkj.service","com.sdkj.controller"})
@Configuration
public class MainConfigOfAutowired {


    @Bean
    public UserDao userDao02(){
        UserDao userDao = new UserDao();
        userDao.setLabel("2");
        return userDao;
    }

    @Bean
    public String website(){
        return new String("www.google.com");
    }

}
