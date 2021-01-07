package com.wht.demo.multithread;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建对象
 *
 * @author wanghtw
 * @date 2019/11/6 17:31
 */
public class CompletableFutureDemo {

    private AtomicInteger i = new AtomicInteger();
    private AtomicDouble j = new AtomicDouble();


    public static void main(String[] args) {
        CompletableFutureDemo demo = new CompletableFutureDemo();
        try {
//            demo.testCreate();
            demo.test();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(System.currentTimeMillis() + ",抛出了异常");
        }
    }

    private void test() throws ExecutionException, InterruptedException {

        ArrayList<CompletableFuture<Integer>> futures = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            int tid = i;
            futures.add(CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 20;
            }).whenComplete((result, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                }
                if (result != null) {
                    System.out.println(result);
                }
            }));
        }

        CompletableFuture<Void> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        System.out.println("sdfsdf");
        result.join();
        result.whenComplete((v,e ) -> {
            System.out.println(v);
            System.out.println(e);
        });
    }

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 40, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1));


    private void testCreate() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(this::getInt, executor);
            CompletableFuture<Double> future2 = CompletableFuture.supplyAsync(this::getDouble, executor);

            CompletableFuture<Double> compose = future.thenApply(integer -> {
                if (integer > 4) {
                    throw new RuntimeException("error");
                }
                return new ClassA(integer);
            }).whenComplete((v, e) -> {
                        if (e == null) {
                            System.out.println(System.currentTimeMillis() + ", run");
                        } else {
                            System.out.println(v + ",抛出了异常");
                        }
                    }
            ).thenCompose(v -> CompletableFuture.supplyAsync(this::getDouble, executor));
        }

        CompletableFuture.allOf();

        //while (i.get() != 10) ;

        long endTime = System.currentTimeMillis();

        System.out.println("cost:" + (endTime - startTime) / 1000);
    }

    public void testCallable(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("doSomething");
                return 1;
            }
        };

        Future<Integer> future = executor.submit(callable);

        try {
            Integer integer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private Integer getInt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.i.getAndIncrement();
        if (i < 2) {
            throw new RuntimeException("error");
        }

        return i;
    }

    private int get(CompletableFutureDemo a) {
        return a.i.get();
    }

    private int getIdFormClassA(ClassA a) {
        if (a == null) return 0;
        System.out.println("getId," + a.id);
        return a.id;
    }

    private double getDouble() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //if (i > 4) {
        //    throw new RuntimeException("error");
        //}

        return j.getAndAdd(1);
    }

    private ClassA setItem() {
        System.out.println("this is : ".concat(String.valueOf(i)));
        ClassA a = new ClassA();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a;
    }


    private class ClassA {

        @Override
        public String toString() {
            return "ClassA{" +
                    "id=" + id +
                    '}';
        }

        private int id;

        public ClassA() {
        }

        public ClassA(int id) {
            this.id = id;
        }

        public int getId() {
            System.out.println("getId," + id);
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
