package com.sdkj.Color;

import org.springframework.beans.factory.FactoryBean;


//创建一个Spring定义的FactoryBean
public class ColorFactoryBean implements FactoryBean<green> {

    //返回一个green对象，这个对象会添加到容器之中
    @Override
    public green getObject() throws Exception {
        return new green();
    }

    //返回添加到容器中的组件的类型
    @Override
    public Class<?> getObjectType() {
        return green.class;
    }

    //return true ： 单实例
    //return false ： 多实例
    //默认多实例
    @Override
    public boolean isSingleton() {
        return false;
    }
}
