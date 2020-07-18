package com.wht.demo.leetCode.offer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * desc.
 *
 * @author wht
 */
public class LRUCacheTest {

    @Test
    public void test(){
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);

        int i = cache.get(1);
        System.out.println(i);

        cache.put(3,3);
        int i1 = cache.get(2);
        System.out.println(i1);

        cache.put(4,4);
        i = cache.get(1);
        System.out.println(i);

        int i2 = cache.get(3);
        System.out.println(i2);

        System.out.println(cache.get(4));

        cache = new LRUCache(2);

        cache.put(2,1);
        cache.put(2,2);
    }

}