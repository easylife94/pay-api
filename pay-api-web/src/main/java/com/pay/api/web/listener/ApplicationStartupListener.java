package com.pay.api.web.listener;

import com.pay.api.client.constants.ZookeeperNamespace;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author chenwei
 * @date 2019/2/11 17:05
 */
@Slf4j
@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    private final CuratorFramework curatorFramework;

    @Autowired
    public ApplicationStartupListener(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    /**
     * 监听到的容器：
     * org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
     * org.springframework.context.annotation.AnnotationConfigApplicationContext
     *
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        if (applicationContext instanceof AnnotationConfigServletWebServerApplicationContext) {
            log.info("servlet容器监听事件");
            initSnowflakeIdWorkerId();
        }
    }

    private void initSnowflakeIdWorkerId() {
        try {
            //使用zookeeper临时顺序节点生成worker id。
            String keyPath = ZookeeperNamespace.WORKER_ID;
            String s = curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(keyPath);
            log.error("创建返回结果：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
