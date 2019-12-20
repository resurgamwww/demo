package com.wht.demo.leetCode;

/**
 * @author wanghtw
 * @date 2019/11/25 22:52
 */
public class Main {
    public static void main(String[] args) {
        //int[] ints = {1, 2, 3, 4, 5, 6, 7};
        int[] ints = {4,1,2,1,2};


        System.out.println(singleNumber(ints));

    }

    /**
     * 向右移动K个元素
     * 该问题归结为：把后K个元素按顺序复制到数组头，前0 -（length - k）个元素挪到k - length处。
     * 要求使用空间复杂度为O(1)的原地算法
     * <p>
     * 本次思路：这个问题可视为每次将开头K个元素和最后K个元素顺序不变的交换位置：交换一次后：
     *
     * @param nums
     * @param k
     */
    static void rotate(int[] nums, int k) {
        if (nums.length <= 1 || k == 0) {
            return;
        }

        /**
         * 需要转换的总长度
         */
        int length = nums.length;
        /**
         * 每次旋转长度
         */
        int m = k;
        /**
         * 存放临时变量
         */
        int temp;
        int i;
        while (length > 1) {
            //交换
            for (i = 0; i < m; i++) {
                temp = nums[i];
                nums[i] = nums[length - m + i];
                nums[length - m + i] = nums[i];
            }

            length -= m;
            if (m << 1 > length) {
                //当剩余总长度不超过旋转长度的2倍时，格式为 A C B，需要转换成 A B C

                //只剩最后一个了，就直接将该位置移到最后一个就行了，
                if (length - m == 1) {

                } else {
                    //还有多的，就缩小旋转长度，继续旋转
                    //m =
                }

            }
        }
    }

    /**
     * 输入一个整形数组，某一个元素只出现一次，其余元素均出现了两次，返回只出现一次的那个
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int result = 0;
        for (int i = 0; i < nums.length -1; i++) {
            result = nums[i] ^ result;

        }

        return result;
    }

}
