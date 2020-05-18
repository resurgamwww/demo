package com.wht.demo.algorithm.util;

import org.springframework.lang.NonNull;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author wht
 * @date 2020/5/7 19:55
 */
public class Util {

    /**
     * 比较两数大小
     *
     * @param a 数一
     * @param b 数二
     * @return 如果a小于b，返回<code>true</code>
     */
    public static boolean less(@NonNull Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换a[i]和a[j]的位置
     *
     * @param a 数组
     * @param i 索引一
     * @param j 索引二
     */
    public static <T> void exchange(@NonNull Object[] a, int i, int j) {
        Object v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void exchange(@NonNull int[] a, int i, int j) {
        int v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void exchange(@NonNull double[] a, int i, int j) {
        double v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void exchange(@NonNull long[] a, int i, int j) {
        long v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static void print(Object[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0, aLength = a.length - 1; i < aLength; i++) {
            sb.append(a[i].toString());
            sb.append(",");
        }
        sb.append(a[a.length - 1]);
        sb.append("]");
        System.out.println(sb.toString());

        PriorityQueue<RandomUtil> queue = new PriorityQueue<>(20, new Comparator<RandomUtil>() {
            @Override
            public int compare(RandomUtil o1, RandomUtil o2) {
                return 0;
            }
        });
    }
}
