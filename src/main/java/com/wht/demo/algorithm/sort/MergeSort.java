package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.Util;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wht
 * @date 2020/5/7 19:20
 */
public class MergeSort {

    /**
     * 将source[low...mid]和source[mid+1...high]的元素归并
     *
     * @param source 原数组
     * @param low    要归并的第一个元素的下标
     * @param mid    要归并的中位元素的下标
     * @param high   要归并的最后一个元素的下标
     */
    private static <T extends Comparable> void merge(T[] source, int low, int mid, int high) {

        int count = high - low;
        T[] temp = Arrays.copyOfRange(source, low, high + 1);

        //l1代表左侧数组未归并的第一个元素的下标,r1代表右侧数组未归并的第一个元素的下标
        int l1 = 0, mi = mid - low, r1 = mi + 1, hi = high - low;
        for (int k = low; k <= high; k++) {
            if (l1 > mi) {
                source[k] = temp[r1++];
            } else if (r1 > hi) {
                source[k] = temp[l1++];
            } else if (Util.less(temp[l1], temp[r1])) {
                source[k] = temp[l1++];
            } else {
                source[k] = temp[r1++];
            }
        }
    }

    /**
     * 自顶向下
     *
     * @param a
     * @param lo
     * @param hi
     */
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);

    }

    /**
     * 自底向上
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        for (int size = 1; size < n; size += size) {
            for (int lo = 0; lo < n - size; lo += size + size) {
                merge(a, lo, lo + size - 1, Math.min(lo + size + size - 1, n - 1));
            }
        }
    }
}
