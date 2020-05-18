package com.wht.demo.algorithm.sort;

/**
 * @author wht
 * @date 2020/5/11 15:25
 */
public class HeapSort {
    public static void sort(Comparable[] a) {
        int size = a.length;
        //构建最大堆
        for (int k = size / 2 - 1; k >= 0; k--) {
            sink(a, k, size);
        }

        while (size > 1) {
            exch(a, 0, --size);
            sink(a, 0, size);
        }
    }

    public static void sink(Comparable[] a, int k, int n) {
        while (leftChild(k) < n) {
            int j = leftChild(k);
            if (j < n - 1 && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, k, j);
            k = j;
        }
    }

    public static int leftChild(int parent) {
        return 2 * parent + 1;
    }

    public static int rightChild(int parent) {
        return 2 * (parent + 1);
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    public static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    public static void swim(Comparable[] a, int k) {
        while (k > 1 && less(a, k / 2, k)) {
            exch(a, k / 2, k);
            k = k / 2;
        }
    }
}
