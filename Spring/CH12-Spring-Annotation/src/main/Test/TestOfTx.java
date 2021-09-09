import com.sdkj.config.MainConfigOfTx;
import com.sdkj.service.TxService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestOfTx {

    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfTx.class);

        TxService txService = (TxService) ac.getBean("txService");
        txService.insert();

        System.out.println("============");

    }
}
