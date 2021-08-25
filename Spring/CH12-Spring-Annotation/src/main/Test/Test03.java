
import com.sdkj.Color.green;
import com.sdkj.config.MainConfig03;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Test03 {

    // @Import: 快速将组件导入容器中，在容器中的名称是也就是id为全类名
    // @Import({**.class,**.class,....})

    // @Import({***.class,....})中***.class 其中可以为ImportSelector
    // ImportSelector的作用是返回需要导入的组件的全类名数组
    //  使用：需要创建一个类实现ImportSelector接口中的selectImports()方法


    @Test
    public void test(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig03.class);

        String[] names = ac.getBeanDefinitionNames();
        for(String name:names){
            System.out.println(name);
        }

    }



    //测试FactoryBean
    @Test
    public void test02(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(MainConfig03.class);
        System.out.println(ac.getBean("factoryBean").getClass());   //com.sdkj.Color.green
    }
}
