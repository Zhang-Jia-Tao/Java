package com.sdkj.config;

import com.aop.LogAspect;
import com.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {

    /*
    * AOP：
    *   指在程序运行期间动态的将某段代码切入到指定方法的指定位置进行运行的编程方式
    *
    *   step：
    *       1）导入AOP依赖，(spring-aspects)
    *       2) 定义一个业务逻辑类 (MathCalculator)   例如有个需求：在业务逻辑运行的时候将日志进行打印(方法运行之前，之后)
    *       3）定义一个日志切面类 (LogAspect)   切面类里面的方法需要动态感知MathCalculator.div运行到哪里了
    *           通知方法：
    *               1）前置通知(@Before): --->logStart()     在目标方法执行之前执行
    *               2）后置通知(@After): --->logEnd()        在目标方法执行之后执行
    *               3）返回通知(@AfterReturning): --->logReturn()    在目标方法正常返回之后执行
    *               4）异常通知(@AfterThrowing): --->logException()  在目标方法抛出遗产之后执行
    *               5）环绕通知(@Around)     手动推进目标方法的执行
    *       4）给切面类的方法标注何时何地运行()
    *       5) 将切面类和业务逻辑类都加入到容器之中
    *       6）必须告诉Spring哪个类是切面类 给切面类上面添加@Aspect注解
    *       7）给配置类中加@EnableAspectJAutoProxy[开启基于注解的aop模式]
    *
    * 源码分析： 从@EnableAspectJAutoProxy
    *       @EnableAspectJAutoProxy中
    *           存在@Import(AspectJAutoProxyRegistrar.class)
    *                   AspectJAutoProxyRegistrar.class 中 存在 registerBeanDefinitions方法
    *                       此方法 会创建一个cls：AnnotationAwareAspectJAutoProxyCreator
    *                           也就是给容器中创建一个类型为AnnotationAwareAspectJAutoProxyCreator的internalAutoProxyCreator组件
    *       重点为AnnotationAwareAspectJAutoProxyCreator：
    *           AnnotationAwareAspectJAutoProxyCreator的继承关系：
    *               ---> AspectJAwareAdvisorAutoProxyCreator
    *                   ---> AbstractAdvisorAutoProxyCreator
    *                       ---> AbstractAutoProxyCreator
    *                               implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
    *                                   重点关注后置处理器(在bean初始化前后做事情)【SmartInstantiationAwareBeanPostProcessor】
    *                                   自动装配BeanFactory【 BeanFactoryAware】
    *       寻找有关后置处理器和BeanFactory有关的方法
    *           AbstractAutoProxyCreator.postProcessBeforeInstantiation()
    *           AbstractAutoProxyCreator.postProcessAfterInitialization()
    *           AbstractAutoProxyCreator.setBeanFactory()
    *
    *           AbstractAdvisorAutoProxyCreator.setBeanFactory()  方法中会调用子类重写的initBeanFactory()方法
    *
    *            AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
    *
    *       Debug 流程分析：
    *           1.传入配置类，创建IOC容器
    *           2.注册配置类，调用refresh() 刷新容器
    *           3. 在refresh()方法中会调用执行registerBeanPostProcessors(beanFactory) ：注册bean后置处理器来方便拦截bean的创建
    *               public static void registerBeanPostProcessors() 方法中
    *                     1) 先获取Ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
    *               		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
    *                            其中 postProcessorNames中就有 internalAutoProxyCreator
    *                     2) 优先注册实现了PriorityOrdered接口的BeanPostProcessor
    *                     3) 再给容器中注册实现了Ordered接口的BeanPostProcessor
    *                     4) 最后注册没有实现任何优先级接口的BeanPostProcessor
    *                     5) 注册BeanPostProcessor 实际上就是创建BeanPostProcessor对象，保存在容器中
    *                           创建internalAutoProxyCreator的BeanPostprocessor
    *                               1.创建Bean的实例
    *                               2.populateBean() : 给bean的各种属性赋值
    *                               3.initializeBean() : 初始化Bean
    *                                       1 invokeAwareMethods() : 处理Aware接口的方法的回调
    *                                       2 applyBeanPostProcessorsBeforeInitialization() : 应用后置处理器
    *                                       3 invokeInitMethods() : 执行初始化方法
    *                                       4 applyBeanPostProcessorsAfterInitialization() ： 应用后置处理器
    *                               4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功
    *                     6) 把BeanPostProcessor注册到BeanFactory中
    *                           beanFactory.addBeanPostProcessor(postProcessor);
    *   ========以上是创建和注册AnnotationAwareAspectJAutoProxyCreator==========
    *           AnnotationAwareAspectJAutoProxyCreator的后置处理器类型为InstantiationAwareBeanPostProcessor 最终还是BeanPostProcessor
    *           4.finishBeanFactoryInitialization(beanFactory)：完成BeanFactory初始化工作；创建剩余单实例Bean
    *               1）遍历获取容器中所有的Bean，依次创建对象 getBean(beanName)
    *                   getBean(beanName) 调用 doGetBean(name, null, null, false)
    *                   doGetBean(name, null, null, false) 调用 getSingleton()
    *                   先从缓存中获取当前Bean，如果能获取到，说明Bean是之前被创建的，就会直接使用，
    *                       否则就会再次创建，只要是创建好的Bean都会被缓存起来
    *               2) createBean() 创建Bean
    *                       【BeanPostProcessor：在Bean对象创建完成初始化前后调用的】
    *                       【InitializationAwareBeanPostProcessor：是在创建Bean实例之前先尝试用后置处理器返回对象】
    *                   1.resolveBeforeInstantiation(beanName, mbdToUse) : 希望后置处理器在此能返回一个代理对象；如果能返回代理对象就使用；如果不能返回对象，就继续
    *                      1）后置处理器先尝试返回对象
    *                       bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName); //拿到所有的后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行postProcessBeforeInstantiation(beanClass, beanName)
	*				            if (bean != null) {
	*					            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
	*				            }
    *                   2.doCreateBean(beanName, mbdToUse, args) : 真正的去创建一个Bean实例 创建流程和3)中6步骤相同
    *
    * AnnotationAwareAspectJAutoProxyCreator的类型为InitializationAwareBeanPostProcessor 的作用：
    *   1）每一个Bean创建之前，调用PostProcessorBeforeInitialization()
    *
    *       按照我们举的例子： mathCalculator、logAspect
    *           1）advisedBeans.containsKey(cacheKey) ： 判断当前Bean是否在advisedBeans中(advisedBeans 保存了所有需要增强的Bean)
    *           2）isInfrastructureClass(Class<?> beanClass) ： 判断当前Bean是否是基础类型 (基础类型：Advice、PointCut、Advisor、AopInfrastructuureBean)
    *               或者为切面(@Aspect)
    *           3）shouldSkip(beanClass, beanName) ：以及判断当前Bean是否需要跳过
    *               获取候选的增强器(切面里面的通知方法)， 候选的增强器的类型为：【List<Advisor> candidateAdvisors】
    *                   每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
    *                   判断每一个增强器是否是AspectJPointcutAdvisor类型的
    *
    *   2）创建对象
    * postProcessorAfterInitialization 中要执行的方法
    *           1）return wrapIfNecessary(bean, beanName, cacheKey);  //如果需要的话就封装
    *                1）findEligibleAdvisors(beanClass, beanName)：获取当前Bean的所有增强器(也就是通知方法)（寻找那些需要切入当前Bean方法的通知方法）
    *                2）findAdvisorsThatCanApply(candidateAdvisors, beanClass, beanName)：获取到能在bean使用的增强器
    *                3）给增强器排序
    *           2）advisedBeans.put(cacheKey, Boolean.TRUE)：保存当前bean在advisedBeans中
    *           3）如果当前Bean需要增强，创建当前Bean的代理对象
    *                   1.获取所有增强器(通知方法)
    *                   2.保存到proxyFactory
    *                   3.创建代理对象：
    *                       JdkDynamicAopProxy：jdk动态代理
    *                       ObjenesisCglibAopProxy：cglib动态代理
    *           4）给容器中返回当前组件的使用cglib增强的代理对象
    *           5）以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
    *
    *====目标方法的执行流程====     也就是分析mathCalculator的执行流程
    *   容器中保存了组件的代理对象(cglib增强后的对象)，这个对象里面保存了详细信息(增强器，目标对象，，，)
    *       1.intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) ： 拦截目标方法的执行
    *               --> 2.List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass) :
    *                       根据proxyFactory对象获取将要执行的目标方法的拦截器
    *                       01 List<Object> interceptorList = new ArrayList<>(advisors.length)  保存所有拦截器
    *                       02 for (Advisor advisor : advisors){
    *                               registry.getInterceptors(advisor);   //遍历所有的增强器，将其转为Interceptors
    *                                   ---》            //将增强器转为List<MethodInterceptor>
    *                                       if (advice instanceof MethodInterceptor) {  //如果是MethodInterceptor，就直接加入到集合中
			                                    interceptors.add((MethodInterceptor) advice);
		                                        }
                                            for (AdvisorAdapter adapter : this.adapters) {  //如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor
                                                if (adapter.supportsAdvice(advice)) {
                                                    interceptors.add(adapter.getInterceptor(advisor));
                                                }
                                            }
    *                          }
    *                   3.if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) ：如果没有拦截器链，则直接执行目标方法
    *                           *拦截器链(每个通知方法又被包装为方法拦截器，上面step2的作用就是如此)
    *                   4.retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
                                ：如果有拦截器链，则会把需要执行的目标方法、拦截器链、等信息传入CglibMethodInvocation()
                        5.拦截器链触发过程
    *                       1）如果没有连接器执行目标方法 或者 拦截器的索引和【拦截器.size-1】想等[也就是递归到了最后一个拦截器] 后直接执行目标方法
    *                       2）链式获取每个拦截器，拦截器执行invoke()方法，每一个拦截器等待下一个拦截器执行完成返回以后再执行
    *                           拦截器链的机制，保证了目标方法和通知方法的执行顺序    ---->也就是利用了递归的原理
    *
    * 总结：
    *   @EnableAspectJAutoProxy 开启AOP功能
    *   @EnableAspectJAutoProxy ： 会给容器中注册一个组件AnnotationAwareAspectJAutoProxyCreator
    *   AnnotationAwareAspectJAutoProxyCreator 是一个后置处理器
    *   容器的创建流程：
    *       1）registerBeanPostProcessor()：注册后置处理器
    *       2) finishBeanFactoryInitialization() ： 初始化剩余的单实例Bean
    *           01 创建业务逻辑组件和切面组件
    *           02 AnnotationAwareAspectJAutoProxyCreator 拦截组件创建过程
    *           03 组件创建完成后，判断组件是否需要增强
    *                需要增强：切面的通知方法，包装为增强器(Advisor)，给业务逻辑组件创建一个代理对象
    *       3）执行目标方法：
    *           01 代理对象执行目标方法
    *           02 CglibAopProxy.Intercept():
    *                   1) 得到目标方法的拦截器链(增强器包装为拦截器MethodInterceptor)
    *                   2) 利用拦截器的链式机制[递归呗]，依次进入每一个拦截器进行执行
    *
    *
     */

    @Bean
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }

}


