package com.wht.demo.leetCode.mingqi;

import org.junit.Test;

import java.util.Arrays;

/**
 * desc.
 *
 * @author wht
 */
public class MingQiTest {

    MingQi qi = new MingQi();

    @Test
    public void merge() {
        //int[] n1 = {1, 2, 3, 0, 0, 0};
        //object.merge(n1,3,new int[]{2,5,6},3);

        int[] n1 = {0};
        qi.merge(n1,0,new int[]{1},1);

        System.out.println(Arrays.toString(n1));
    }

    @Test
    public void isPalindrome(){
        System.out.println(qi.isPalindrome("A man, a plan, a canal: Panama"));
    }
}