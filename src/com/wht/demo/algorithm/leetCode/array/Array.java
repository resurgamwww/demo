package com.wht.demo.algorithm.leetCode.array;

import java.util.*;

/**
 * @author wanghtw
 * @date 2019/12/20 23:13.
 */
public class Array {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        int[] data;
        data = new int[20000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }
        //data = new int[]{3,2,3};

        int[] ints = twoSum(data, 39999);
        for (int i : ints) {
            System.out.println(String.format("%d  -  %d", i, data[i]));
        }

        System.out.println(System.currentTimeMillis() - l);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        //两层循环，找到一样的放到结果集里，每次找到时，需要去判断一下结果集里这个数字已经出现的次数来防止重复计算 或者 用一个map记录下外层有对应数据的数字和内层的位置，这样每次找之前先看一下外层元素是否已经在map中了，如果在的话，内层可以直接从指定的位置开始。
        //这样的好处是，如果数据量非常大，一次循环无法读出所有数据的情况下，这样是能工作的。但是其实速度最快的还是能先排好序，这样查找的开销能缩小非常多。不过对于这个场景，就需要预处理，类似数据库索引那样，事先根据某个字段排序做索引，然后才有可能

        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        List<Integer> list = new ArrayList<>();
        Integer lastIndex = 0;
        for (int i = 0; i < nums1.length; i++) {
            lastIndex = map.get(nums1[i]);
            int j = lastIndex == null ? 0 : lastIndex + 1;
            while (j < nums2.length) {

                if (nums1[i] == nums2[j]) {
                    map.put(nums2[j], j);
                    list.add(nums2[j]);
                }
                j++;
            }
        }

        int[] array = new int[list.size()];
        for (int i1 = 0; i1 < list.size(); i1++) {
            array[i1] = list.get(i1);
        }

        return array;
    }

    public static int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>(nums.length);

        int j;
        for (int i = 0; i < nums.length; i++) {
            j = target - nums[i];
            if (map.containsKey(j)) {
                return new int[]{i, map.get(j)};
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }
}
