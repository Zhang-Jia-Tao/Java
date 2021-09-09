import com.sdkj.config.MainConfigOfExternal;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestOfExternal {

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfExternal.class);
        /*  执行结果
        * mybeanfactory is running and the method postProcessBeanFactory also execute
            当前BeanFactry中有8个Bean
            [org.springframework.context.annotation.internalConfigurationAnnotationProcessor,
            *  org.springframework.context.annotation.internalAutowiredAnnotationProcessor,
            *  org.springframework.context.annotation.internalCommonAnnotationProcessor,
            *  org.springframework.context.event.internalEventListenerProcessor,
            *  org.springframework.context.event.internalEventListenerFactory,
            * mainConfigOfExternal,
            * myFactory,
            * green]
            green
        * */
    }
}
