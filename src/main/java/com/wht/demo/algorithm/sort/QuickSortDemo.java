package com.wht.demo.algorithm.sort;

/**
 * 快排核心思路是排序前选一个基数，然后将用两个索引分别从头尾开始遍历，与基数比较，如果左侧的大于基数则交换到右侧，如果右侧的小于基数则交换到左侧。最后
 * 遍历一遍后，以基数将原数组切分，分别对两部分排序，直到数组长度 = 2。
 *
 * @author wht
 */
public class QuickSortDemo {

    public static void sort(Integer[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(Integer[] array, int begin, int end) {
        //判断特殊情况
        if (begin < 0 || end >= array.length) {
            return;
        }
        if (end - begin <= 1) {
            compareAndSwap(array, begin, end);
            return;
        }

        int p = partition(array, begin, end);

        sort(array, begin, p - 1);
        sort(array, p + 1, end);
    }

    /**
     * 分区
     *
     * @return 基数
     */
    public static int partition(Integer[] array, int begin, int end) {
        //三值取中法取基数
        int mid = begin + ((end - begin) >> 1);
        compareAndSwap(array, begin, end);
        compareAndSwap(array, begin, mid);
        compareAndSwap(array, mid, end);
        int partition = array[mid];
        //为了方便处理，将mid放到倒数第二个位置
        swap(array, mid, end - 1);
        mid = end - 1;

        //现在头尾和基数已经确定，开始交换位置
        begin++;
        end--;
        while (begin < end) {
            while (array[begin] <= partition && begin < end) {
                begin++;
            }
            while (array[end] >= partition && begin < end) {
                end--;
            }

            if (begin < end) {
                swap(array, begin, end);
            } else {
                break;
            }
        }

        swap(array, begin, mid);
        return begin;
    }

    public static void compareAndSwap(Integer[] array, int i, int j) {
        if (j < 0 || i >= array.length) {
            System.out.println("11");
        }
        if (array[i] > array[j]) {
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
        }
    }

    /**
     * 交换 i，j的位置
     */
    public static void swap(Integer[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
