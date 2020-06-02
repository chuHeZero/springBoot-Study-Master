package com.chuhezero;


import com.chuhezero.bean.ChuHe;
import com.chuhezero.bean.Monkey;
import com.chuhezero.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest {

    @Test
    public void test(){
        //启动IOC容器
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //思考：为什么抛 NoSuchBeanDefinitionException
        //BeanDefinition :bean定义    承载 bean的属性，例如：scope(作用域)、className、method-init(初始化方法)
        //bean :交给IOC容易管理的对象就是Bean

        //beanName : id、name
        //BeanDefinitionRegistry : bean定义注册器
        //注册 bean：registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
        context.getBean("user");

        //如何将chuHe这个bean交给 spring管理？
        // bean注册原理：将class对应的 beanDefinition 最终缓存到 beanDefinitionMap key为：name---value为：beanDefinition

        /*第一种注册------------------------------start------------------------------*/

        //第一步：设置bean定义
        BeanDefinition beanDefinition = new RootBeanDefinition(ChuHe.class);

        //第二步：调用context(应用上下文)的 registerBeanDefinition方法注册 bean定义
        // registerBeanDefinition方法 最终会调用工厂的 registerBeanDefinition 的方法
        //BeanDefinition existingDefinition = this.beanDefinitionMap.get(beanName)  --->此处 beanDefinitionMap就是一个concurrentHashMap(线程安全的HashMap)

        //注册 BeanDefinition
        context.registerBeanDefinition("chuHe",beanDefinition);

        //填充属性
        beanDefinition.getPropertyValues().add("name","chuHe");
        beanDefinition.getPropertyValues().add("age",16);

        //设置注入方式 1、类内部写一个有参构造方法并用@Autowired注解标注
        //2、通过 beanDefinition 设置 setAutowireMode  值为3时：构造器贪婪模式  选择构造器参数(bean)列表多的
        ((RootBeanDefinition) beanDefinition).setAutowireMode(3);

        /*第一种注册------------------------------end------------------------------*/


        //第二种注册 直接注册类 最终也会调用 registerBeanDefinition 方法
        context.register(ChuHe.class);



        context.getBean("chuHe");


        //思考：不注册 beanDefinition，是否可以直接将对象交给spring容器管理？

        // 对象是交给spring的BeanFactory(bean工厂)创建
        Monkey monkey = new Monkey();

        //获取工厂
        DefaultListableBeanFactory defaultListableBeanFactory = context.getDefaultListableBeanFactory();

        //将单例对象缓存到单例对象池中    singletonObjects(单例对象池)--->底层为：ConcurrentHashMap
        //将普通的对象变成一个 bean
        defaultListableBeanFactory.registerSingleton("monkey",monkey);
    }

}
