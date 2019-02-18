package com.pay.api.web.listener;

import com.pay.api.core.utils.ClassScaner;
import com.pay.api.core.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Set;

/**
 * @author chenwei
 * @date 2019/2/11 17:05
 */
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("启动监听器");

    }

}
