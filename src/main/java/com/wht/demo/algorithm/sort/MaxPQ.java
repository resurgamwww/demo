package com.wht.demo.algorithm.sort;

import org.springframework.lang.NonNull;

import java.util.Arrays;

/**
 * 优先队列，为了方便理解，pq[0]不使用，从pq[1]开始存放
 *
 * @author wht
 * @date 2020/5/10 12:05
 */
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;

    private int size = 0;
    private int capacity = 100;

    /**
     * 创建一个优先队列
     */
    public MaxPQ() {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    /**
     * 创建一个优先队列并指定初始容量
     *
     * @param maxCapacity 优先队列的初始容量
     */
    public MaxPQ(int maxCapacity) {
        pq = (Key[]) new Comparable[maxCapacity + 1];
        capacity = maxCapacity;
    }

    /**
     * 创建一个优先队列，队列中包含a[]的所有元素
     *
     * @param a 初始元素
     */
    public MaxPQ(@NonNull Key[] a) {
        Arrays.sort(a);

        int length = a.length;
        pq = (Key[]) new Comparable[length * 4 / 3 + 1];

        //sort一般都是按递增排列，优先队列是按递减排列，复制时需要反转
        for (int i = 0, j = length; i < length; i++, j--) {
            pq[j] = a[i];
        }
    }

    /**
     * 向优先队列中插入一个元素
     *
     * @param v 要插入的元素
     */
    void insert(Key v) {
        pq[++size] = v;
        swim(size);

    }

    /**
     * 返回队列中的最大元素
     *
     * @return 最大元素
     */
    Key max() {
        return pq[1];
    }

    /**
     * 删除并返回最大元素
     *
     * @return 最大元素
     */
    Key delMax() {
        Key max = pq[1];
        exch(1, size--);
        pq[size + 1] = null;
        sink(1);
        return max;
    }

    /**
     * 返回队列是否为空
     *
     * @return 如果队列为空，返回<code>true</code>
     */
    boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回队列中的元素个数
     *
     * @return 队列中的元素个数
     */
    public int size() {
        return size;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

}
