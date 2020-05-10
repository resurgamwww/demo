package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.Util;
import org.springframework.lang.NonNull;

/**
 * @author wht
 * @date 2020/5/7 22:53
 */
public class QuickSort {

    public static void sort(Comparable[] a) {
        if (a == null) {
            return;
        }
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int low, int high) {
        if (a == null) {
            return;
        }
        if (low >= high + 5) {
            InsertSort.sort(a, low, high);
            return;
        }

        int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        //数组分为a[lo...i-1],a[i],a[i+1...high]
        int i = low, j = high + 1;
        Comparable v = a[low];

        while (true) {
            while (Util.less(a[++i], v)) {
                if (i == high) {
                    break;
                }
            }
            while (Util.less(v, a[--j])) {
                if (j == low) {
                    break;
                }
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

}
