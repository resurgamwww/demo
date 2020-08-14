package com.wht.demo.multithread;

import com.wht.demo.algorithm.util.RandomUtil;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 最基础的本地生产者、消费者队列
 *
 * @author wht
 */
public class ConsumerDemo2 {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, String.valueOf(threadNumber.getAndIncrement()));
        }
    });

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        executor.submit(new Producer(queue));

        ReentrantLock lock = new ReentrantLock();
        for (int i = 1; i <= 3; i++) {
            executor.submit(new ConditionConsumer(lock, queue, i));
        }
    }

}

class ConditionConsumer implements Runnable {

    private final ReentrantLock lock;

    private final LinkedBlockingQueue<Integer> queue;

    private int number;

    public ConditionConsumer(ReentrantLock lock, LinkedBlockingQueue<Integer> queue, int number) {
        this.lock = lock;
        this.queue = queue;
        this.number = number;
    }

    @Override
    public void run() {
        Integer poll = null;
        while (true) {
            try {
                lock.lock();
                poll = queue.peek();
                if (poll != null && poll % number == 0) {
                    queue.take();
                } else {
                    continue;
                }
                Thread.sleep(RandomUtil.uniform(50, 100));
                System.out.println("消费者" + number + ":" + poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }
}