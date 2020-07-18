package com.wht.demo.algorithm.util;

import com.google.gson.Gson;
import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * @author wht
 * @date 2020/5/7 19:55
 */
public class Util {

    public static Gson gson = new Gson();

    /**
     * 比较两数大小
     *
     * @param a 数一
     * @param b 数二
     * @return 如果a小于b，返回<code>true</code>
     */
    public static <T extends Comparable<T>> boolean less(@NonNull T a, T b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 交换a[i]和a[j]的位置
     *
     * @param a 数组
     * @param i 索引一
     * @param j 索引二
     */
    public static <T> void swap(@NonNull Object[] a, int i, int j) {
        Object v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void swap(@NonNull int[] a, int i, int j) {
        int v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void swap(@NonNull double[] a, int i, int j) {
        double v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void swap(@NonNull long[] a, int i, int j) {
        long v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static <T> void swap(@NonNull char[] a, int i, int j) {
        char v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    public static void print(Object[] a) {
        System.out.println(toString(a));
    }

    public static void print(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    public static String toString(Object[] a){
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0, aLength = a.length - 1; i < aLength; i++) {
            sb.append(a[i].toString());
            sb.append(",");
        }
        sb.append(a[a.length - 1]);
        sb.append("]");

        return sb.toString();
    }
}
