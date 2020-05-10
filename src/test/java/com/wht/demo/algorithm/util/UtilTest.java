package com.wht.demo.algorithm.util;

import org.junit.Test;

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
    }
}