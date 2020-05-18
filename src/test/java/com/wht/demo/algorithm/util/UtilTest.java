package com.wht.demo.algorithm.util;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author wht
 * @date 2020/5/7 21:17
 */
public class UtilTest {

    @Test(expected = NullPointerException.class)
    public void less() {
        Util.less(null,200);
    }

    @Test
    public void test(){
        int[] a = new int[9];
        System.out.println(a.length);

        ArrayList<Integer> list = new ArrayList<>(20);
        System.out.println(list.size());
    }
}