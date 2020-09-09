package com.wht.demo.multithread;

import java.util.LinkedHashSet;
import java.util.concurrent.ForkJoinPool;

/**
 * desc.
 *
 * @author wht
 */
public class ParallelDemo {
    public static void main(String[] args) {

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < 20; i++) {
            set.add(i);
        }

        ForkJoinPool pool = new ForkJoinPool(20);

        long begin = System.currentTimeMillis();

        pool.submit(() -> set.parallelStream().forEach(x -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(x);
        }));

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
