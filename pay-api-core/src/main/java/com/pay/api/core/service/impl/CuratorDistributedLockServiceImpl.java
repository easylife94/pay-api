package com.pay.api.core.service.impl;

import com.pay.api.client.constants.ZookeeperNamespace;
import com.pay.api.core.service.IDistributedLockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author chenwei
 * @date 2019/4/11 18:28
 */
@Slf4j
@Service
public class CuratorDistributedLockServiceImpl implements IDistributedLockService, InitializingBean {

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private final CuratorFramework curatorFramework;

    @Autowired
    public CuratorDistributedLockServiceImpl(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    @Override
    public void lock(String key) {
        String keyPath = ZookeeperNamespace.LOCKS + "/" + key;
        while (true) {
            try {
                curatorFramework
                        .create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(keyPath);
                break;
            } catch (Exception e){
                try {
                    if (connectedSemaphore.getCount() <= 0) {
                        connectedSemaphore = new CountDownLatch(1);
                    }
                    connectedSemaphore.await();
                } catch(InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean tryLock(String key, long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void unlock(String key) {
        String keyPath = ZookeeperNamespace.LOCKS + "/" + key;
        try {
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建watcher事件
     * @param path
     */
    private void addWatcher(String path) throws Exception{
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, path, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                if (oldPath.contains(path)) {
                    //释放计数器，让当前的请求获取锁
                    connectedSemaphore.countDown();
                }
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String path = ZookeeperNamespace.LOCKS;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWatcher(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
