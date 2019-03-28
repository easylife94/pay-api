package com.pay.api.web.api;

import com.pay.api.web.PayApiWebApplicationTests;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenwei
 * @date 2019/1/16 17:59
 */
public class PayApiCuratorTest extends PayApiWebApplicationTests {

    @Autowired
    private CuratorFramework curatorFramework;

    @Test
    public void testCurator() throws Exception {
        curatorFramework
                .create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                .forPath("/test");
    }
}