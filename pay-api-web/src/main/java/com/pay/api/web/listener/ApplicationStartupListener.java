package com.pay.api.web.listener;

import com.pay.api.client.constants.ZookeeperNamespace;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.core.service.IIdService;
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

import java.util.List;

/**
 * @author chenwei
 * @date 2019/2/11 17:05
 */
@Slf4j
@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {


    @Value("${snowflake.worker-id-max}")
    private Integer workerIdMax;

    private final CuratorFramework curatorFramework;
    private final IIdService iIdService;

    @Autowired
    public ApplicationStartupListener(CuratorFramework curatorFramework, IIdService iIdService) {
        this.curatorFramework = curatorFramework;
        this.iIdService = iIdService;
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
            //使用zookeeper临时顺序节点生成worker id。0~31
            String baseKeyPath = ZookeeperNamespace.WORKER_ID;
            if (curatorFramework.checkExists().forPath(baseKeyPath) == null) {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(baseKeyPath);
            }
            Integer workerId = null;
            List<String> workerIds = curatorFramework.getChildren().forPath(baseKeyPath);
            log.info("已有workerIds:{}", workerIds);
            if (workerIds != null && workerIds.size() > 0) {
                if (workerIds.size() > workerIdMax) {
                    log.error("workerId数量已达到上限：{}", workerIdMax);
                    throw new IllegalStateException("workerId数量已达到上限：" + workerIdMax);
                }
                for (int i = 0; i <= workerIdMax; i++) {
                    if (!workerIds.contains(String.valueOf(i))) {
                        workerId = i;
                        break;
                    }
                }
            } else {
                workerId = 0;
            }

            curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(baseKeyPath + "/" + workerId);

            iIdService.setWorkerId(workerId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PayApiException("初始化workerId失败");
        }

    }

}
