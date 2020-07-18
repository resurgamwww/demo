package com.wht.demo.leetCode.offer;

import com.wht.demo.algorithm.util.Util;
import org.junit.Test;

/**
 * desc.
 *
 * @author wht
 */
public class NumIslandsTest {

    @Test
    public void numIslands() {
        String s = "[[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"1\",\"0\",\"0\"],[\"0\",\"0\",\"0\",\"1\",\"1\"]]";

        char[][] array = Util.gson.fromJson(s, char[][].class);

        NumIslands temp = new NumIslands();
        int i = temp.numIslands(array);

        System.out.println(i);
    }
}