package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.Util;

/**
 * @author wht
 * @date 2020/5/9 17:07
 */
public class InsertSort {

    public static void sort(Comparable[] a) {
        if (a == null) {
            return;
        }

        sort(a, 0, a.length - 1);
    }

    /**
     * 插入排序
     * <p>
     * 从0...n-1依次将第当前个元素和下一个元素进行比较，如果当前元素大于下一个元素，那么将下一个元素按顺序插入到之前的元素中
     *
     * @param a
     * @param low
     * @param high
     */
    public static void sort(Comparable[] a, int low, int high) {
        if (a == null) {
            return;
        }
        if (low >= high) {
            return;
        }

        //按升序排序
        for (int i = low + 1, j = i; i <= high; j = ++i) {
            Comparable temp = a[i];

            //将last与[low,i]之间的元素进行比较，找到大于last的最小元素，插入到该元素前的位置。如果都小于last，进行下一轮循环
            while (j > low && Util.less(temp, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }

            a[j] = temp;
        }

    }
}
