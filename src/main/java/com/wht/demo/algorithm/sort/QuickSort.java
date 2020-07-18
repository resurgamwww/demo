package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.Util;

import java.util.Arrays;

/**
 * @author wht
 * @date 2020/5/7 22:53
 */
public class QuickSort {

    public static void sort(Comparable[] a) {
        if (a == null) {
            return;
        }

        sort3Ways(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int high) {
        if (a == null) {
            return;
        }
        if (low >= high) {
            return;
        }

        if (low + 5 >= high) {
            InsertSort.sort(a, low, high);
            return;
        }

        //int j = partition(a, low, high);
        int j = partitionWithMedian3(a, low, high);

        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    /**
     * 三向切分的快速排序，适用于有大量重复元素的场景
     */
    public static void sort3Ways(Comparable[] a, int low, int high) {
        if (high <= low) {
            return;
        }

        int lt = low, i = low + 1, gt = high;
        Comparable v = a[low];

        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                Util.swap(a, lt++, i++);
            } else if (cmp > 0) {
                Util.swap(a, i, gt--);
            } else {
                i++;
            }
        }

        sort3Ways(a, low, lt - 1);
        sort3Ways(a, gt + 1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        //数组分为a[low...i-1],a[i],a[i+1...high]
        int i = low, j = high + 1;
        Comparable v = a[low];

        while (true) {
            while (Util.less(a[++i], v)) {
                //if (i == high) {
                //    break;
                //}
            }
            while (Util.less(v, a[--j])) {
                //if (j == low) {
                //    break;
                //}
            }

            if (i >= j) {
                break;
            }

            //交换位置
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        //交换位置
        Comparable temp = a[low];
        a[low] = a[j];
        a[j] = temp;

        return j;
    }

    private static int partitionWithMedian3(Comparable[] a, int low, int high) {
        //数组分为a[low...i-1],a[i],a[i+1...high]
        int i = low, j = high - 1;
        int index = median3(a, low, high);
        Comparable v = a[index];

        if (high - low < 3) {
            return i;
        }

        while (true) {
            while (Util.less(a[++i], v)) {
            }
            while (Util.less(v, a[--j])) {
            }

            if (i >= j) {
                break;
            }

            //交换位置
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        //交换位置
        Comparable temp = a[high - 1];
        a[high - 1] = a[i];
        a[i] = temp;

        return i;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{2, 1};
        int i = partitionWithMedian3(a, 0, 1);
        System.out.println(i);
        System.out.println(Arrays.toString(a));
    }


    private static int median3(Comparable[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }
        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }
        // 将枢纽元放置到right-1位置
        swapReferences(a, center, right - 1);
        return right - 1;
    }

    public static <T> void swapReferences(T[] a, int index1, int index2) {
        T tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

}
