package com.pay.api.core.service;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁服务
 *
 * @author chenwei
 * @date 2019/4/9 15:29
 */
public interface IDistributedLockService {

    /**
     * 加锁
     *
     * @param key 锁对象
     */
    void lock(String key);

    /**
     * 尝试加锁
     *
     * @param key  锁对象
     * @param time 尝试加锁时间数值
     * @param unit 尝试加锁时间单位
     * @return 加锁是否成功
     */
    boolean tryLock(String key, long time, TimeUnit unit);

    /**
     * 解锁
     *
     * @param key 锁对象
     */
    void unlock(String key);
}
