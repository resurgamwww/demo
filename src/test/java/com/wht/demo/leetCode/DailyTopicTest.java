package com.wht.demo.leetCode;

import com.sun.org.apache.regexp.internal.RESyntaxException;
import com.wht.demo.leetCode.offer.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * desc.
 *
 * @author wht
 */
public class DailyTopicTest {

    DailyTopic dailyTopic = new DailyTopic();

    @Test
    public void reverseWords() {
        String s = dailyTopic.reverseWords("Let's take LeetCode contest");
        System.out.println(s);
    }

    @Test
    public void testTrans() {
        Integer a = null;
    }

    @Test
    public void testTopK() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};

        int[] result = dailyTopic.topKFrequent(nums, 2);

        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testCombinationSum() {
        List<List<Integer>> list = dailyTopic.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(list);
    }

    @Test
    public void testInorderTraversalWithIteration() {
        TreeNode node;
        List<Integer> list;
        //node = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null));
        //list = main.inorderTraversalWithIteration(node);

        node = new TreeNode(3, new TreeNode(1, null, null), new TreeNode(2, null, null));

        list = dailyTopic.inorderTraversalWithIteration(node);

        System.out.println(list);
    }

    @Test
    public void testUniqueOccurrences() {

        dailyTopic.uniqueOccurrences(new int[]{1, 2, 2, 3, 3, 3, 4, 5, 5, 6, 6, 6});
    }

    @Test
    public void testSumNumbers() {
        dailyTopic.sumNumbers(new TreeNode(1, new TreeNode(2), new TreeNode(3)));
    }

    @Test
    public void testValidMountainArray() {
        int[] a = new int[]{14, 82, 89, 84, 79, 70, 70, 68, 67, 66, 63, 60, 58, 54, 44, 43, 32, 28, 26, 25, 22, 15, 13, 12, 10, 8, 7, 5, 4, 3};
        boolean b = dailyTopic.validMountainArray(a);
        System.out.println(b);
    }

    @Test
    public void testInsert() {
        int[][] result;

        //result = dailyTopic.insert(new int[][]{{1, 5}}, new int[]{2, 3});
        //assert result.length == 1;

        result = dailyTopic.insert(new int[][]{{2, 5}, {6, 7}, {8, 9}}, new int[]{0, 1});
        assert result.length == 4;


        for (int[] ints : result) {
            System.out.println(Arrays.toString(ints));
        }

    }
}