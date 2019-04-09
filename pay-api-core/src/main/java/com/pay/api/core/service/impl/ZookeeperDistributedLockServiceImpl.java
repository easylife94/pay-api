package com.pay.api.core.service.impl;

import com.pay.api.client.constants.ZookeeperNamespace;
import com.pay.api.client.exception.PayApiException;
import com.pay.api.core.service.IDistributedLockService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.StandardLockInternalsDriver;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 基于zookeeper的分布式锁服务实现
 *
 * @author chenwei
 * @date 2019/4/9 15:37
 */
@Service
public class ZookeeperDistributedLockServiceImpl implements IDistributedLockService {

    private final CuratorFramework curatorFramework;
    private final Map<String, InterProcessMutex> locks;

    @Autowired
    public ZookeeperDistributedLockServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
        this.locks = new HashMap<>();
    }

    @Override
    public void lock(String key) {
        try {
            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
            InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, fullPath);
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean tryLock(String key, long time, TimeUnit unit) {
        try {
            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
            InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, fullPath);
            return interProcessMutex.acquire(time, unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void unlock(String key) {
//        try {
//            String fullPath = "/pay-api" + ZookeeperNamespace.LOCKS + "/" + key;
//            InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, fullPath);
//            interProcessMutex.release();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
