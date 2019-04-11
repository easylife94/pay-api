package com.pay.api.core.service.impl;

import com.pay.api.client.constants.ZookeeperNamespace;
import com.pay.api.core.service.IDistributedLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 基于zookeeper的分布式锁服务实现
 *
 * @author chenwei
 * @date 2019/4/9 15:37
 */
@Slf4j
@Service
public class ZookeeperDistributedLockServiceImpl implements IDistributedLockService {


    private final CuratorFramework curatorFramework;
    private final ConcurrentHashMap<String, InterProcessMutex> locks;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    public ZookeeperDistributedLockServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
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
            interProcessMutex.release();
            if (!interProcessMutex.isAcquiredInThisProcess()) {
                locks.remove(fullPath,interProcessMutex);
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
            log.error("state1:{}",curatorFramework.getState());
            log.error("创建");
            interProcessMutex = new InterProcessMutex(curatorFramework, key);
            InterProcessMutex absent = locks.putIfAbsent(key, interProcessMutex);
            if (absent != null) {
                log.error("创建失败");
                interProcessMutex = absent;
            }
            log.error("state2:{}",curatorFramework.getState());
        }
        return interProcessMutex;
    }


//    @Override
//    public void lock(String key) throws Exception {
//        String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
//        while (true) {
//            try {
//                curatorFramework
//                        .create()
//                        .creatingParentsIfNeeded()
//                        .withMode(CreateMode.EPHEMERAL)
//                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                        .forPath(fullPath);
//                log.info("success to acquire lock for path:{}", fullPath);
//                break;
//            } catch (Exception e) {
//                log.info("failed to acquire lock for path:{}", fullPath);
//                log.info("while try again .......");
//                try {
//                    if (countDownLatch.getCount() <= 0) {
//                        countDownLatch = new CountDownLatch(1);
//                    }
//                    countDownLatch.await();
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean tryLock(String key, long time, TimeUnit unit) {
//        return false;
//    }
//
//    @Override
//    public void unlock(String key) {
//        try {
//            String fullPath = ZookeeperNamespace.LOCKS + "/" + key;
//            if (curatorFramework.checkExists().forPath(fullPath) != null) {
//                curatorFramework.delete().forPath(fullPath);
//            }
//        } catch (Exception e) {
//            log.error("failed to release lock");
//        }
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        String lockPath = ZookeeperNamespace.LOCKS;
//        try {
//            if (curatorFramework.checkExists().forPath(lockPath) == null) {
//                curatorFramework.create()
//                        .creatingParentsIfNeeded()
//                        .withMode(CreateMode.PERSISTENT)
//                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                        .forPath(lockPath);
//            }
//            addWatcher(lockPath);
//            log.info("root path 的 watcher 事件创建成功");
//        } catch (Exception e) {
//            log.error("connect zookeeper fail，please check the log >> {}", e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 创建 watcher 事件
//     */
//    private void addWatcher(String path) throws Exception {
//        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, path, false);
//        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
//        cache.getListenable().addListener((client, event) -> {
//            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
//                String oldPath = event.getData().getPath();
//                log.info("上一个节点 "+ oldPath + " 已经被断开");
//                if (oldPath.contains(path)) {
//                    //释放计数器，让当前的请求获取锁
//                    countDownLatch.countDown();
//                }
//            }
//        });
//    }
}
