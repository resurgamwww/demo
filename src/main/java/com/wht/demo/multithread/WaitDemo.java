package com.wht.demo.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * desc.
 *
 * @author wht
 */
public class WaitDemo {
    public static void main(String[] args) {

        WaitDemo demo = new WaitDemo();

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        try {
            demo.wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();

    }

    public void doSomething() throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
        condition.signalAll();
    }
}
