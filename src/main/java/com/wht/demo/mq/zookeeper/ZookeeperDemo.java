package com.wht.demo.mq.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc.
 *
 * @author wht
 */
public class ZookeeperDemo {
    static CuratorFramework client;

    public static void main(String[] args) throws Exception {
        ZookeeperDemo demo = new ZookeeperDemo();
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();
        demo.testBlocking();

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        client.close();
    }

    public void testBlocking() throws Exception {
        try {
            client.create().withMode(CreateMode.EPHEMERAL).forPath("/block");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                Thread.sleep(8000);
                client.delete().forPath("/block");
                System.out.println("删除节点");
            } catch (Exception e) {
                System.out.println("删除失败");
            }


        }).start();

        //尝试获取锁，拿的到就继续操作，操作完带着版本号删除锁。拿不到就注册监听事件然后阻塞。监听到事件后会恢复，然后再去尝试获取锁。

        Stat stat = null;
        try {
            stat = client.checkExists().creatingParentsIfNeeded().forPath("/block");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (stat == null) {
            try {
                client.create().withMode(CreateMode.EPHEMERAL).forPath("/block");
            } catch (Exception e) {
                //加锁失败，抛出异常。检查异常状态，如果是锁已经被其他人持有了，那么注册监听器，开始阻塞，或者直接返回失败。


            }

            //这时已经拿到锁，返回即可。
        } else {

            //失败处理，加监听器，然后阻塞
            System.out.println("加锁失败");

            ReentrantLock lock = new ReentrantLock();

            try {
                client.getData().usingWatcher(new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {

                        if (watchedEvent.getType() == Event.EventType.NodeDeleted){
                            try {
                                System.out.println("成功加锁");
                                client.create().withMode(CreateMode.EPHEMERAL).forPath("/block");
                                //doSomething
                                System.out.println("业务逻辑");

                                client.delete().forPath("/block");

                                System.out.println("释放锁");
                            } catch (Exception e) {

                                System.out.println("jiasuoshibai");
                            }
                        }

                        lock.unlock();
                    }
                }).forPath("/block");
            } catch (Exception e) {
                System.out.println("加监听器失败了，这种情况应该抛出异常");
                throw e;
            }

            System.out.println("锁住");
            lock.lock();
        }
    }

    public void test() throws Exception {
        client.create().forPath("/test");

        String s = client.create().creatingParentsIfNeeded().forPath("/test/test", "测试内容".getBytes());
        System.out.println(s);

        client.setData().forPath("/test", "new".getBytes());

        byte[] bytes = client.getData().forPath("/test");
        System.out.println(new String(bytes));

        client.delete().deletingChildrenIfNeeded().forPath("/test");

        //client.setData().forPath("/test", "修改后的内容".getBytes());

        //bytes = client.getData().forPath("/test");
        //System.out.println(new String(bytes));
    }

    public void recipesLock() {
        String lockPath = "/lock";

        InterProcessMutex lock = new InterProcessMutex(client, lockPath);

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, lockPath);

        try {
            lock.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recipesDistAtomicInt() throws Exception {
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, "/atomicInteger", new RetryNTimes(3, 1000));

        AtomicValue<Integer> value = atomicInteger.add(8);

        boolean b = value.succeeded();
    }

    public void distributedBarrier() throws Exception {
        DistributedBarrier barrier = new DistributedBarrier(client, "/barrier");

        barrier.setBarrier();
        barrier.waitOnBarrier();

        barrier.removeBarrier();
    }

    public void transaction() throws Exception {

        CuratorOp create = client.transactionOp().create().forPath("/test");
        CuratorOp set = client.transactionOp().setData().forPath("/test");
        List<CuratorTransactionResult> list = client.transaction().forOperations(create, set);
    }

    public void leader() {
        new LeaderSelector(client, "/", new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {

            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });
    }

    public void listen() throws Exception {

        Stat stat = client.checkExists().forPath("/lock");

        client.create().withMode(CreateMode.EPHEMERAL).forPath("/lock");

        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Event.EventType type = event.getType();
                System.out.println(type);

                try {
                    if (type != Event.EventType.NodeDeleted) {
                        client.getData().usingWatcher(this).forPath("/lock");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).forPath("/lock");

        client.setData().forPath("/lock", "第1次更改内容".getBytes());
        Thread.sleep(1000);

        stat = client.checkExists().forPath("/lock");

        client.setData().forPath("/lock", "第2次更改内容".getBytes());
        Thread.sleep(1000);

        stat = client.checkExists().forPath("/lock");

        client.setData().forPath("/lock", "第3次更改内容".getBytes());
        Thread.sleep(1000);

        stat = client.checkExists().forPath("/lock");
    }

}
