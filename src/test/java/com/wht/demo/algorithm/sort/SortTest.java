package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.RandomUtil;
import com.wht.demo.algorithm.util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wht
 * @date 2020/5/7 22:18
 */
public class SortTest {
    private Integer[] a;

    @Before
    public void before(){
    }

    @After
    public void after(){
        Util.print(a);
    }

    @Test
    public void testMergeSort() {
        a = new Integer[]{2, 4, 1, 5, 3, 9, 4, 0};

        //MergeSort.sort(a, 0, a.length - 1);
        MergeSort.sort(a);

    }

    @Test
    public void testQuickSort(){
        a = new Integer[9];
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }

        QuickSort.sort(a);
    }

    @Test
    public void testInsertSort(){
        a = new Integer[9];
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }

        InsertSort.sort(a);
    }

    @Test
    public void testDumpSort(){
        a = new Integer[9];
        for (int i = 0; i < a.length; i++) {
            a[i] = a.length - i;
        }

        RandomUtil.shuffle(a);
        Util.print(a);

        HeapSort.sort(a);
    }
}