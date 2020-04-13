package com.wht.demo.completablefutrueDemo.get;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 创建对象
 *
 * @author wanghtw
 * @date 2019/11/6 17:31
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main main = new Main();
        main.testCreate();
    }

    private void testCreate() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        int i;
        ArrayList<ClassA> list = new ArrayList<>(200);
        for (i = 0; i < 10000; i++) {
            CompletableFuture<ClassA> future = CompletableFuture.completedFuture(this.setItem(i));
            future.whenComplete((v,e) -> list.add(v));
        }

        long endTime = System.currentTimeMillis();

        System.out.println((endTime - startTime) / 1000);

    }


    private ClassA setItem(int i){
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
        private int id;

        public ClassA() {
        }

        public ClassA(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
