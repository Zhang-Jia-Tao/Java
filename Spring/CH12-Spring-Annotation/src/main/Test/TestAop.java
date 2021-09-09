import com.aop.MathCalculator;
import com.sdkj.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAop {

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mathCalculator = (MathCalculator) ac.getBean("mathCalculator");
        int i = 1,j=1;
        Integer res = mathCalculator.div(i, j);
    }
}
