package com.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;




@Aspect         //标注为切面类
public class LogAspect {

    @Before("execution(public Integer com.aop.MathCalculator.div(int,int))")
    public void logStart(){
        System.out.println("除法运行....参数列表为:");
    }

    @After("execution(public Integer com.aop.MathCalculator.div(int,int))")
    public void logEnd(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        System.out.println(""+joinPoint.getSignature().getName()+"结束....{"+args.toString()+"}");
    }

    @AfterReturning(value="execution(public Integer com.aop.MathCalculator.div(int,int))",returning = "res")
    public void logReturn(Object res){
        System.out.println("除法正常返回....运行结果为:{"+res+"}");
    }

    public void logException(){
        System.out.println("除法运行异常....异常信息为:");
    }
}
