package com.xxyy.quandaiban1.infrastructure.common;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xy
 * @date 2023-10-21 21:01
 */

@Component
public class Ioc implements ApplicationContextAware {

    private static ApplicationContext ctx;

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

}
