package com.sdkj.User;


import org.springframework.stereotype.Component;

@Component
public class Teenager {

    private String name;

    public Teenager(){

    }


//           3) 通过使用JSR250：   在init、destroy方法上添加
//                @PostConstruct：在Bean创建完成并属性赋值完成后执行初始化方法
//                @PreDestroy： 在容器销毁Bean之前，通知进行清理工作


    public void init(){
        System.out.println("teenager...init...");
    }

    public void destroy(){
        System.out.println("teenager...destory...");
    }

}
