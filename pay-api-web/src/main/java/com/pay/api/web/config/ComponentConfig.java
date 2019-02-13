package com.pay.api.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义组件配置类
 *
 * @author chenwei
 * @date 2019-02-04
 */
@Configuration
public class ComponentConfig {

    private static final Logger logger = LoggerFactory.getLogger(ComponentConfig.class);

    private final AutowireCapableBeanFactory capableBeanFactory;

    @Autowired
    public ComponentConfig(AutowireCapableBeanFactory capableBeanFactory) {
        this.capableBeanFactory = capableBeanFactory;
        initTradeHandleAdapterComponent();
    }

    public void initTradeHandleAdapterComponent(){
        System.out.println(capableBeanFactory);



    }

}
