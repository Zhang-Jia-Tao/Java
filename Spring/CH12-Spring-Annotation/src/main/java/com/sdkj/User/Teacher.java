package com.sdkj.User;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//通过让Bean实现InitializingBean(定义初始化逻辑)、实现DisposableBean(定义销毁逻辑)

@Component
public class Teacher implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {
        System.out.println("teacher...destroy...");
    }

    //在创建且初始化数据之后，调用afterPropertiesSet()
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("teacher...afterPropertiesSet...");
    }


    public Teacher(){
        System.out.println("teacher....constructor");
    }


}
