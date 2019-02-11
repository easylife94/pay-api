package com.pay.api.web.listener;

import com.pay.api.core.PlatformComponent;
import com.pay.api.core.utils.ClassScaner;
import com.pay.api.core.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author chenwei
 * @date 2019/2/11 17:05
 */
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("启动监听器");

        //TODO 注入平台组件
        logger.info("注入平台组件");
        String packageName = "com.pay.api.core.platform";
        Set<Class> classes = ClassScaner.scan(packageName,PlatformComponent.class);
        for(Class clazz : classes){
            PlatformComponent annotation = (PlatformComponent) clazz.getAnnotation(PlatformComponent.class);
            System.out.println(annotation.value());
            SpringContextUtil.setBean(clazz,annotation.value().toString());
        }
    }

}
