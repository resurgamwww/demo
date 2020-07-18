package com.wht.demo.multithread;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * desc.
 *
 * @author wht
 */
public class Test {

    static class TestThread implements Runnable {

        private Semaphore semaphore;

        public TestThread() {
        }

        public TestThread(Semaphore latch) {
            this.semaphore = latch;
        }

        @Override
        public void run() {

            //while (true) {
            //    System.out.println(Thread.currentThread().getName() + "is running");
            //}
            try {
                System.out.println(semaphore.availablePermits());
                semaphore.acquire();

                System.out.println(semaphore.availablePermits());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "中断了");
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Semaphore count = new Semaphore(1);
        //Thread thread = new Thread(new TestThread(count), "测试类");
        //thread.start();
        //
        //thread.sleep(200);
        //
        //thread.interrupt();

        PriorityBlockingQueue<Long> queue = new PriorityBlockingQueue<Long>(100, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return -o1.compareTo(o2);
            }
        });
        for (int i = 0; i < 20; i++) {
            queue.add((long) (Math.random() * 20));
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
