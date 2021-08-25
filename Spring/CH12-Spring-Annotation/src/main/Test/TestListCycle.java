
import com.sdkj.User.Student;
import com.sdkj.config.MainConfigLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestListCycle {

    @Test
    public void test(){
        //创建IOC容器
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);

        Student student = (Student) ac.getBean("student01");





    }
}
