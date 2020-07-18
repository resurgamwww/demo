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
public class Main {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",retryPolicy);
        client.start();

        client.create().forPath("/test","测试内容".getBytes());


        while (System.in.read() != '0'){
        }

        client.close();


    }
}
