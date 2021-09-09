package com.beanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Controller
public class MyFactory implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        System.out.println("mybeanfactory is running and the method postProcessBeanFactory also execute");
        int count = beanFactory.getBeanDefinitionCount();
        String[] names = beanFactory.getBeanDefinitionNames();

        System.out.println("当前BeanFactry中有"+count+"个Bean");
        System.out.println(Arrays.asList(names));
    }
}
