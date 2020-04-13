package com.wht.demo.algorithm.sort;

/**
 * @author wanghtw
 * @date 2019/12/26 15:15
 */
public class QuickSort {

    /**
     * 思路:
     * 1.定一个基数，将数组按基数分成左右两边
     * 2.左边从左到右遍历，比基数大的放到左边，右边从右到左循环，比基数小的放到右边
     * 3.遍历结束后，对左右两个数组分别再次重复上述过程，直到数组中只有1个元素
     *
     * 这里用三值取中法，后续版本可以考虑对于长度小于10的用插入排序
     * @param array
     */
    public static void quickSort(int[] array, int left , int right){

        if (array.length == 0){
            return ;
        }

        //取中值

    }

    /**
     * 对于数组array，返回中值
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int getMedian(int[] array, int left , int right){
        int median = (left + right) >> 1;

        if (array[left] > array[right]){
            swap(array,left,right);
        }

        return 0;
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = array[i];
    }
}
