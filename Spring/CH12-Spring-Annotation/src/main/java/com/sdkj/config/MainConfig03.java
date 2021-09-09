package com.sdkj.config;

import com.sdkj.Color.ColorFactoryBean;
import com.sdkj.Color.ImportSelector;
import com.sdkj.Color.blue;
import com.sdkj.Color.red;
import com.sdkj.User.User;
import com.sdkj.condition.MacCondition;
import com.sdkj.condition.WindowCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Conditional({MacCondition.class})
@Import({ImportSelector.class})
public class MainConfig03 {

    /*
    *@Conditional:
    *   按照一定的条件进行判断，满足条件给容器注册bean
    *   可以在类上面，也可以在方法上面
    *   在类上面的含义是 若满足则：判断方法上有无@Conditional，若没有则直接注册到容器中，若有
    *   则再次进行条件的判断 为真 注册到容器，为false 依旧不注册到容器之中
    *   若在类上面直接判断为false，则类中所有均不会注册到容器之中
    * */

    @Conditional({WindowCondition.class})
    @Bean
    public User Linghuchong(){
        return new User("令狐冲",20);
    }


    @Conditional({MacCondition.class})
    @Bean
    public User Dongfang(){
        return new User("东方不败",20);
    }


    /*
    * 给容器中注册组件的方式：
    *   1）包扫描+组件标注注解(@Component、@Controller、@Service、@Repository) [用于自己写的类]
    *   2) @Bean [用于第三方的组件的创建]
    *   3) @Import [快速给容器中导入一个组件]
    *   4）使用Spring提供的FactoryBean(工厂bean)
    * */


    //使用FactoryBean。给容器中注册组件
    //默认获取到的是FactoryBean调用getObject创建的对象
    @Bean
    public ColorFactoryBean factoryBean(){
        return new ColorFactoryBean();
    }




}
