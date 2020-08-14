package com.wht.demo.multithread;

import com.wht.demo.algorithm.util.RandomUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 最基础的本地生产者、消费者队列
 *
 * @author wht
 */
public class ConsumerDemo {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,20, TimeUnit.SECONDS,new LinkedBlockingQueue<>(), new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, String.valueOf(threadNumber.getAndIncrement()));
        }
    });

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        executor.submit(new Producer(queue));

        for (int i = 0; i < 5; i++) {
            executor.submit(new Consumer(queue));
        }
    }

}

class Producer implements Runnable {

    private LinkedBlockingQueue<Integer> queue;

    public Producer(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                queue.put(i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前容量" + queue.size());
        }
    }
}

class Consumer implements Runnable {

    private LinkedBlockingQueue<Integer> queue;

    public Consumer(LinkedBlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Integer poll = null;
        while (true) {
            try {
                poll = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (poll != null){
                try {
                    Thread.sleep(RandomUtil.uniform(50, 100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者" + Thread.currentThread().getName() + ":" + poll);
            }
        }

    }
}