package com.chuhezero.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean {

    private Class aClass;

    public MyFactoryBean(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public Object getObject() throws Exception {
        return aClass.newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return aClass;
    }
}
