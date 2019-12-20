package com.wht.demo.leetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 判断数组中是否有重复数据，有的话返回true，没有的话返回false
 * @author wanghtw
 * @date 2019/12/6 18:52
 */
public class IsExists {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(null,123);
        Object o = map.get(null);
        if (o != null){
            System.out.println(o);
        }
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>(nums.length);
        for (int i : nums) {
            if (!set.add(i)){
                return true;
            }
        }
        return false;
    }

}
