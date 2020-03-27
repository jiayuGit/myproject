package com.example.demo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext a) throws BeansException {
        applicationContext=a;
    }
    public static<V> V getBeanByName(String name,Class<V> args){
        if (applicationContext==null){
            return null;
        }
        return applicationContext.getBean(name,args);
    }
    public static<V> V getBeanByType(Class<V> args){
        if (applicationContext==null){
            return null;
        }
        return applicationContext.getBean(args);
    }

}
