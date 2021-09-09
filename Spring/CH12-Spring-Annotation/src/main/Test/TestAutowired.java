import com.sdkj.config.MainConfigOfAutowired;
import com.sdkj.controller.MyWeb;
import com.sdkj.dao.UserDao;
import com.sdkj.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAutowired {

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);

        UserService userService = (UserService)ac.getBean("userService");
        userService.Print();
        UserDao userDao = new UserDao();
        userDao.setLabel("3");
        userService.dosome(userDao);

        MyWeb myWeb = (MyWeb) ac.getBean("myWeb");
        System.out.println(myWeb);
    }
}
