package com.pay.api.core.service.impl;

import com.pay.api.client.constants.ZookeeperNamespace;
import com.pay.api.core.service.IDistributedLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 基于zookeeper的分布式锁服务实现
 *
 * @author chenwei
 * @date 2019/4/9 15:37
 */
@Slf4j
@Service
public class ZookeeperDistributedLockServiceImpl implements IDistributedLockService {


    private final CuratorFrameworkFactory.Builder curatorFrameworkBuilder;
    private final ConcurrentHashMap<String, InterProcessMutex> locks;

    @Autowired
    public ZookeeperDistributedLockServiceImpl(CuratorFrameworkFactory.Builder curatorFrameworkBuilder) {
        this.curatorFrameworkBuilder = curatorFrameworkBuilder;
        this.locks = new ConcurrentHashMap<>();
    }

    /**
     * 加重入锁
     *
     * @param key 锁对象
     */
    @Override
    public void lock(String key) {
        try {
            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
            InterProcessMutex interProcessMutex = getLock(fullPath);
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("加锁异常，线程：{}", Thread.currentThread().getName());
        }
    }

    /**
     * 尝试加重入锁
     *
     * @param key  锁对象
     * @param time 尝试加锁时间数值
     * @param unit 尝试加锁时间单位
     * @return 当且仅当获取锁成功时返回true，否则返回false
     */
    @Override
    public boolean tryLock(String key, long time, TimeUnit unit) {
        try {
            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
            InterProcessMutex interProcessMutex = getLock(fullPath);
            return interProcessMutex.acquire(time, unit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("尝试获取分布式锁异常，key:{}，time:{}，unit:{}，异常信息：{}", key, time, unit, e.getMessage());
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key 锁对象
     */
    @Override
    public void unlock(String key) {
        try {
            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
            InterProcessMutex interProcessMutex = locks.get(fullPath);
            if (!interProcessMutex.isAcquiredInThisProcess()) {
                locks.remove(fullPath, interProcessMutex);
            } else {
                interProcessMutex.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解锁异常，线程：{}，key：{},异常信息：{}", Thread.currentThread().getName(), key, e.getMessage());
        }
    }


    /**
     * 从本地锁对象池获取锁
     *
     * @param key 对象key
     * @return 锁对象
     */
    private InterProcessMutex getLock(String key) {
        InterProcessMutex interProcessMutex;
        if (locks.containsKey(key)) {
            interProcessMutex = locks.get(key);
        } else {
            CuratorFramework client = curatorFrameworkBuilder.build();
            client.start();
            interProcessMutex = new InterProcessMutex(client, key);
            InterProcessMutex absent = locks.putIfAbsent(key, interProcessMutex);
            if (absent != null) {
                interProcessMutex = absent;
            }
        }
        return interProcessMutex;
    }
}
