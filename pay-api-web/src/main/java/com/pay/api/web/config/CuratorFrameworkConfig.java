package com.pay.api.web.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * curator框架配置
 * zookeeper使用api
 *
 * @author chenwei
 * @date 2019/3/28 14:43
 */
@Configuration
public class CuratorFrameworkConfig {

    @Value("${curator.connect-string}")
    private String connectString;
    @Value("${curator.session-timeout-ms}")
    private Integer sessionTimeoutMs;
    @Value("${curator.connection-timeout-ms}")
    private Integer connectionTimeoutMs;
    @Value("${spring.application.name}")
    private String applicationName;


    @Bean
    public CuratorFramework curatorFramework() throws InterruptedException {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(exponentialBackoffRetry)
                .namespace(applicationName)
                .build();
        curatorFramework.start();

        //阻塞直到连接创建，超时时间:10s
        curatorFramework.blockUntilConnected(10, TimeUnit.SECONDS);
        return curatorFramework;
    }

    @Bean
    public CuratorFrameworkFactory.Builder curatorFrameworkFactoryBuilder() throws InterruptedException {
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 10);
        return CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(exponentialBackoffRetry)
                .namespace(applicationName);
    }
}
