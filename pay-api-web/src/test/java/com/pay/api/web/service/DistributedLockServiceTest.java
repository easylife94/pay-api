package com.pay.api.web.service;

import com.pay.api.web.PayApiWebApplicationTests;
import com.pay.common.core.service.IDistributedLockService;
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
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while ( COUNT < 100 ) {
                    try {
                        long begin = System.currentTimeMillis();
                        distributedLockService.lock(key);
                        System.out.println("t1 lock,ms : " + (System.currentTimeMillis() - begin));
                        COUNT++;
                        System.out.println("t1 count:" + COUNT);
                        begin = System.currentTimeMillis();
                        distributedLockService.unlock(key);
                        System.out.println("t1 unlock ms :"  + (System.currentTimeMillis() - begin));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                    while ( COUNT < 100 ) {
                        try {
                            long begin = System.currentTimeMillis();
                            distributedLockService.lock(key);
                            System.out.println("t2 lock,ms : " + (System.currentTimeMillis() - begin));
                            COUNT++;
                            System.out.println("t2 count:" + COUNT);
                            begin = System.currentTimeMillis();
                            distributedLockService.unlock(key);
                            System.out.println("t2 unlock ms :"  + (System.currentTimeMillis() - begin));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

            }
        });
        t1.start();
        t2.start();


        Thread.sleep(500000);
    }
}
