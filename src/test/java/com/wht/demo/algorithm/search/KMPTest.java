package com.wht.demo.algorithm.search;

import org.junit.Before;
import org.junit.Test;

/**
 * desc.
 *
 * @author wht
 */
public class KMPTest {

    String pat = "ababac";
    String text = "ababadababaababacaba";

    @Before
    public void before() {

    }

    @Test
    public void test() {
        KMP kmp = new KMP(pat);
        int i = kmp.search(text);
        System.out.println(i);
        assert i != -1;
    }

}