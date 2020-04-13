package com.wht.demo.algorithm.leetCode.array;

import com.wht.demo.factoryDemo.factory.abstractFactory.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * @author wanghtw
 * @date 2019/12/20 23:13.
 */
public class Array {

    public static void print(int[] array) {
        for (int i : array) {
            System.out.println(i);
        }
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

    public static void rotate(int[] nums, int k) {
        if (nums.length < 1) {
            return;
        }


        //每次只挪动一个位置，时间复杂度O(N)
        //int last;
        //int temp;
        //for (int i = 0; i < k; i++) {
        //    last = nums[nums.length - 1];
        //    for (int j = 0, numsLength = nums.length  ; j < numsLength; j++) {
        //        temp = nums[j];
        //        nums[j] = last;
        //        last = temp;
        //    }
        //}

        //每次直接挪动K个位置
        int n = nums.length;
        k = k % n;
        int lastIndex = (n - k) % n;
        int last = nums[lastIndex];
        int temp;
        int j = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            temp = nums[j];
            nums[j] = last;
            last = temp;

            j = (j + k) % n;
            //关键在于这里，可能会出现第一轮走完正好又回到原位置的情况，需要手动将位置+1，从下一位开始循环
            if (j == count) {
                count++;
                j = count;
                last = nums[(n + j - k) % n];
            }
        }

        //第三种，反转，先将整个数组反转，然后将前K个元素反转，后N-K个元素反转

    }

    /**
     * 检查是否是有效的数独，核心是查找重复的字符串，对每行、列、九宫格分别查找一次
     *
     * @param board 数独
     * @return {@code true} 如果数独有效
     */
    public static boolean isValidSudoku(char[][] board) {

        //if (board.length < 3 || board[0].length < 3) {
        //    return false;
        //}
        //
        //int length = 9;
        //
        //HashSet<String> set = new HashSet<String>(length);
        ////检查每行
        //for (int i = 0; i < length; i++) {
        //    for (char c : board[i]) {
        //        if (c != '.') {
        //            if (!set.add(String.valueOf(c))) {
        //                return false;
        //            }
        //        }
        //    }
        //    set.clear();
        //}
        //
        ////检查每列
        //
        //for (int i = 0; i < length; i++) {
        //    for (int j = 0; j < length; j++) {
        //        if (board[j][i] != '.') {
        //            if (!set.add(String.valueOf(board[j][i]))) {
        //                return false;
        //            }
        //        }
        //    }
        //    set.clear();
        //}
        //
        ////检查每个九宫格
        //length = 3;
        //int x, y = 0;
        //for (int i = 0; i < length; i++) {
        //    for (int j = 0; j < length; j++) {
        //
        //        for (int k = 0; k < length; k++) {
        //            for (int m = 0; m < length; m++) {
        //                x = i * length + k;
        //                y = j * length + m;
        //
        //                if (board[x][y] != '.') {
        //                    if (!set.add(String.valueOf(board[x][y]))) {
        //                        return false;
        //                    }
        //                }
        //            }
        //        }
        //        set.clear();
        //    }
        //}
        //return true;

        //下边这种解法的核心思想是把二维数组中每个数字的位置映射到一个二进制形式的整数上,利用按位与和按位或的性质判断是否重复
        if (board == null)
            return false;
        if (board.length != 9)
            return false;
        int[] map = new int[9];
        for (int y = 0; y < 9; y++) {
            if (board[y] == null || board[y].length != 9)
                return false;
            for (int x = 0; x < 9; x++) {
                int key = board[y][x] - '1'; //key:数字
                if (key >= 0 && key <= 8) {  //1~9有效数字
                    int index = (1 << x)     //Value:位置编码，最低9位存放列号
                            | (1 << (y + 9))    //中间9位存放行号
                            | (1 << (x / 3 + y / 3 * 3 + 18));  // z为9宫格区域序号
                    int old = map[key];
                    if ((old & index) == 0) //无重复，则按位或，加入位置集合
                        map[key] = old | index;
                    else    //有重复
                        return false;
                }
            }
        }
        return true;
    }

    public static void rotate(int[][] matrix) {
        if (matrix.length < 2) {
            return;
        }


        int temp;
        int temp1;
        int x, y;
        int nextX, nextY;

        int n = matrix.length - 1;

        int max = matrix.length / 2;
        if (matrix.length % 2 != 0) {
            max++;
        }


        //对于前i行(1<= i <= length / 2)，依次旋转该行中从第 i 到 length -i - 1 列的元素，注意四角是特殊情况，需要减1
        for (int i = 0; i < max; i++) {
            for (int j = i; j < matrix.length - i - 1; j++) {
                //所以每次旋转其实都是 x,y => y,n-x

                //上方 -> 右侧
                //i,j -> j,n-i

                x = i;
                y = j;
                nextX = y;
                nextY = n - x;
                temp = matrix[nextX][nextY];
                matrix[nextX][nextY] = matrix[x][y];
                x = nextX;
                y = nextY;

                //右侧 -> 底部
                //j,n-i -> n-i,n-j

                nextX = y;
                nextY = n - j;
                temp1 = matrix[nextX][nextY];
                matrix[nextX][nextY] = temp;
                temp = temp1;
                x = nextX;
                y = nextY;

                //底部 -> 左侧
                //n-i,n-j -> n-j, i

                nextX = y;
                nextY = i;
                temp1 = matrix[nextX][nextY];
                matrix[nextX][nextY] = temp;
                temp = temp1;

                //左侧 -> 上方
                matrix[i][j] = temp;

                //所以每次旋转其实都是 x,y -> y,n-x，简化一下里边这四步可以用一个循环来代替
            }
        }

    }
}