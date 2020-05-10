package com.wht.demo.algorithm.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        for (Integer integer : a) {
            System.out.println(integer);
        }
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
}