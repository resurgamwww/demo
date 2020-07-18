package com.wht.demo.mq.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * desc.
 *
 * @author wht
 */
public class ZookeeperDemo {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,1);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2182",retryPolicy);
        client.start();


        client.create().forPath("/test","测试内容".getBytes());

        byte[] bytes = client.getData().forPath("/test");
        System.out.println(new String(bytes));

        //client.setData().forPath("/test", "修改后的内容".getBytes());

        //bytes = client.getData().forPath("/test");
        //System.out.println(new String(bytes));
    }
}
