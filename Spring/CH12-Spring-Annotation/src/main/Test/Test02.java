import com.sdkj.User.User;
import com.sdkj.config.MainConfig02;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test02 {

    //@Lazy 用在配置类中的方法上(用于创建对象的方法上)
    //测试懒加载

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig02.class);
        System.out.println("这时并不会出现user03正在创建中\n==========");

        //只有第一次获取时，才会创建bean于容器中
        User user = (User) ac.getBean("user03");
        System.out.println(user.getName());

    }


}
