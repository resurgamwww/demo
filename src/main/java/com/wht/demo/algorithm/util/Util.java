package com.wht.demo.algorithm.util;

import org.springframework.lang.NonNull;

/**
 * @author wht
 * @date 2020/5/7 19:55
 */
public class Util {

    public static boolean less(@NonNull Comparable a , Comparable b){
        return a.compareTo(b) < 0;
    }


}
