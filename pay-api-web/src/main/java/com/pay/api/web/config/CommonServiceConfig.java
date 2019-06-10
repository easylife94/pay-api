package com.pay.api.web.config;

import com.pay.common.client.constants.ZookeeperCommonNamespace;
import com.pay.common.core.service.IDistributedLockService;
import com.pay.common.core.service.IIdService;
import com.pay.common.core.service.IWorkerService;
import com.pay.common.core.service.impl.IdServiceImpl;
import com.pay.common.core.service.impl.ZookeeperDistributedLockServiceImpl;
import com.pay.common.core.service.impl.ZookeeperWorkerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通用服务配置
 *
 * @author chenwei
 * @date 2019/4/17 15:15
 */
@Slf4j
@Configuration
public class CommonServiceConfig {

    /**
     * zookeeper分布式锁服务
     *
     * @param curatorFrameworkBuilder curator建造者对象
     * @return 返回分布式锁服务
     */
    @Bean
    public IDistributedLockService distributedLockService(CuratorFrameworkFactory.Builder curatorFrameworkBuilder) {
        return new ZookeeperDistributedLockServiceImpl(curatorFrameworkBuilder, ZookeeperCommonNamespace.LOCKS);
    }

    /**
     * worker协调服务
     *
     * @param curatorFrameworkBuilder curator建造者对象
     * @return 返回worker协调服务
     */
    @Bean
    public IWorkerService workerService(CuratorFrameworkFactory.Builder curatorFrameworkBuilder) {
        return new ZookeeperWorkerServiceImpl(curatorFrameworkBuilder);
    }

    /**
     * 全局唯一Id服务
     *
     * @param workerService worker协调服务
     * @return 返回全局唯一Id服务
     */
    @Bean
    public IIdService idService(IWorkerService workerService) {
        return new IdServiceImpl(workerService);
    }
}
