package com.pay.api.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring 上下文的工具类
 *
 * @author chenwei
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     *
     */
    private static AutowireCapableBeanFactory autowireCapableBeanFactory;

    /**
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
        SpringContextUtil.autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 自定注入bean
     *
     * @param beanClass bean类
     * @param name bean名称
     * @param <T> bean类
     * @return bean实例
     */
    public static <T> T setBean(Class<T> beanClass, String name) {
        //TODO
        T bean = autowireCapableBeanFactory.createBean(beanClass);
        autowireCapableBeanFactory.configureBean(bean, name);
        return bean;
    }
}