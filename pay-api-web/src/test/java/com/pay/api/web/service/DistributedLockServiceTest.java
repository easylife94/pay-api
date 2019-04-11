package com.pay.api.web.service;

import com.pay.api.core.service.IDistributedLockService;
import com.pay.api.web.PayApiWebApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试服务接口
 *
 * @author chenwei
 * @date 2019-03-04
 */
public class DistributedLockServiceTest extends PayApiWebApplicationTests {

    @Autowired
    private IDistributedLockService distributedLockService;

    private static int COUNT = 0;

    @Test
    public void lock() throws InterruptedException {
        //测试并发
        final String key = "test";
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                distributedLockService.lock(key);
//                COUNT++;
//                System.out.println("t1 count:" + COUNT);
//                distributedLockService.unlock(key);
//            }
//        });
//        t1.start();
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                distributedLockService.lock(key);
//                COUNT++;
//                System.out.println("t2 count:" + COUNT);
//                distributedLockService.unlock(key);
//            }
//        });
//        t1.start();
//        t2.start();
//
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while ( COUNT < 100 ) {
                        distributedLockService.lock(key);
                        System.out.println("t1 lock");
                        COUNT++;
                        System.out.println("t1 count:" + COUNT);
                        System.out.println("t1 unlock");
                        distributedLockService.unlock(key);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while ( COUNT < 100 ) {
                        distributedLockService.lock(key);
                        System.out.println("t2 lock");
                        COUNT++;
                        System.out.println("t2 count:" + COUNT);
                        Thread.sleep(1000);
                        System.out.println("t2 unlock");
                        distributedLockService.unlock(key);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
