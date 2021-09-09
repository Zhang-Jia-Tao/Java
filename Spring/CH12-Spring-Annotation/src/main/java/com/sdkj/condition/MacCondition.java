package com.sdkj.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

//        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig03.class);
//        int beanDefinitionCount = ac.getBeanDefinitionCount();

        Environment environment = conditionContext.getEnvironment();
        String property = environment.getProperty("os.name");
        if(property.contains("Mac")){
            return true;
        }
        return false;
    }
}
