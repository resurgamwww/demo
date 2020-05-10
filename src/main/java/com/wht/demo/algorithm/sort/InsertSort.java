package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.Util;
import org.springframework.lang.NonNull;

import javax.swing.*;

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
     * 从0...n-1依次将第当前个元素和下一个元素进行比较，如果当前元大于下一个元素，那么将下一个元素按顺序插入到之前的元素中
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

        for (int i = low, j = i, n = a.length - 1; i < n; j = ++i) {
            Comparable next = a[i + 1];
            while (Util.less(next, a[j])) {
                a[j + 1] = a[j];
                if (j == low) {
                    break;
                }
                j--;
            }
            a[j] = next;
        }

    }
}
