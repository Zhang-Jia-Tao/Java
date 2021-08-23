package com.sdkj;

import com.sdkj.User.User;
import com.sdkj.config.MainConfig;
import com.sdkj.controller.MyWeb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);

        User user = (User) ac.getBean("user");
        System.out.println(user);

        String[] namefortype = ac.getBeanNamesForType(MyWeb.class);
        for(String name:namefortype){
            System.out.println(name);
        }

        System.out.println("========");

        String[] name = ac.getBeanDefinitionNames();
        for(String call:name){
            System.out.println(call);
        }

    }
}
