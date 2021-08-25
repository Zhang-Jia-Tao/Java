import com.sdkj.User.User;
import com.sdkj.config.MainConfig;
import com.sdkj.config.MainConfig02;
import com.sdkj.controller.MyWeb;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);

        System.out.println("Ioc容器已经创建完成");
        /*
        * 测试@Scope
        * */
        User user01 = (User) applicationContext.getBean("user");
        User user02 = (User) applicationContext.getBean("user");
        System.out.println(user01 == user02);
        //为false时，表明@Scope("prototype") 多实例的

        System.out.println("========");

        String[] names = applicationContext.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }
        int count = applicationContext.getBeanDefinitionCount();
        System.out.println(count);

        int age = user01.Count();
        System.out.println(age);
        int age02 = user02.Count();
        System.out.println(age02);
        int age03 = user01.Count();
        System.out.println(age03);


    }

    //@Scope注解还可以用在 使用@ComponentScan+(@Component、@Service、@Controller、@Repository
    @org.junit.Test
    public void test02(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig.class);
        MyWeb myWeb = (MyWeb)ac.getBean("myWeb");
        MyWeb myWeb1 = (MyWeb)ac.getBean("myWeb");
        System.out.println(myWeb == myWeb1);
        System.out.println(myWeb);
    }

}
