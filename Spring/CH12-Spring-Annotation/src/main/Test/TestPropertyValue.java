import com.sdkj.User.User_Value;
import com.sdkj.config.MainConfigPropertyValue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class TestPropertyValue {
    ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigPropertyValue.class);


    public void AllPrintBean(ApplicationContext ac){
        String[] beanName = ac.getBeanDefinitionNames();
        for(String bean:beanName){
            System.out.println(bean);
        }
    }

    @Test
    public void test(){
        AllPrintBean(ac);

        //测试@PropertySource加载外部配置文件
        User_Value userValue = (User_Value) ac.getBean("userValue");
        System.out.println("======>"+userValue);

        //有一种方式可以通过配置文件的key获取value
        // 因为外部配置文件会加载到运行中的环境变量中，所以可以以此来获取
        Environment environment = ac.getEnvironment();
        String property = environment.getProperty("person.nickname");
        System.out.println("nickname="+property);

    }
}
