package com.chuhezero.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        //将monkey的 beanDefinition注入到容器中
        //registry.registerBeanDefinition("monkey", new RootBeanDefinition(Monkey.class));

        BeanDefinition beanDefinition = new RootBeanDefinition(MyFactoryBean.class);

        registry.registerBeanDefinition("myFactoryBean",beanDefinition);

        //获取构造器 传参
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue("com.chuhezero.bean.ChuHe");
    }
}
