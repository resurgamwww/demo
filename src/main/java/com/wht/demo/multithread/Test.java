package com.wht.demo.multithread;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc.
 *
 * @author wht
 */
public class Test<T> extends CompletableFuture<T>{

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
                semaphore.drainPermits();
                System.out.println(semaphore.availablePermits());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "中断了");
            }


        }
    }

    public void completableFuture(){
        CompletableFuture<Object> future = new CompletableFuture<>();

    }

    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Integer integer = 2;
        Double d = 2.0;

        System.out.println(d.intValue() == integer);

        //Semaphore count = new Semaphore(1);
        //Thread thread = new Thread(new TestThread(count), "测试类");
        //thread.start();
        //
        //thread.sleep(200);
        //
        //thread.interrupt();

        //PriorityBlockingQueue<Long> queue = new PriorityBlockingQueue<Long>(100, new Comparator<Long>() {
        //    @Override
        //    public int compare(Long o1, Long o2) {
        //        return -o1.compareTo(o2);
        //    }
        //});
        //for (int i = 0; i < 20; i++) {
        //    queue.add((long) (Math.random() * 20));
        //}
        //
        //while (!queue.isEmpty()) {
        //    System.out.println(queue.poll());
        //}

        //System.out.println(1 << 30);
        //System.out.println(Integer.MAX_VALUE);

        //ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new PriorityBlockingQueue<>(40));
        //
        //
        //for (int i = 0; i < 50; i++) {
        //    executor.execute(new Task(i,i / 10));
        //}
        //
        //
        //Thread.sleep(2000);
        //
        //executor.shutdown();
        //
        //Thread.currentThread().join();

    }

    static class Task implements Runnable,Comparable<Task>{

        public Task(int type) {
            this.type = type;
        }

        public Task(int no, int type) {
            this.no = no;
            this.type = type;
        }

        int no;
        int type;

        @Override
        public int compareTo(Task o) {
            return this.type - o.type;
        }

        @Override
        public void run() {
            System.out.printf("no: %d, type: %d \n",no,type);

            lock.lock();

            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();

            lock.unlock();

        }
    }
}
