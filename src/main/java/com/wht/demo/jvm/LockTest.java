package com.wht.demo.jvm;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc.
 *
 * @author wht
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println((-4080 >> 4));
        System.out.println((4080 >>> 4));
        final ReentrantLock lock = new ReentrantLock(false);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();

                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();

                System.out.println("t1 unlock");
                lock.unlock();

                lock.lock();
            }
        },"t1");
        t1.start();
        Thread.sleep(300);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("2 start");
                boolean b = lock.tryLock();
                System.out.println("2 尝试获取锁，结果:" + b);
                lock.lock();
                System.out.println("2 获取锁");

                while (true){

                }
            }
        },"t2");

        t2.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                while (true);
            }
        },"t3").start();

        //while (true) {
        //
        //}
    }
}
