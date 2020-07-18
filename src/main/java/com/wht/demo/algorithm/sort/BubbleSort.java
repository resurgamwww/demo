package com.wht.demo.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author wht
 */
public class BubbleSort {
    public static Integer[] bubbleSort(Integer[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
