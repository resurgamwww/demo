package com.wht.demo.leetCode;

import com.wht.demo.leetCode.offer.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * desc.
 *
 * @author wht
 */
public class MainTest {

    Main main = new Main();

    @Test
    public void reverseWords() {
        String s = main.reverseWords("Let's take LeetCode contest");
        System.out.println(s);
    }

    @Test
    public void testTrans(){
        Integer a = null;
    }

    @Test
    public void testTopK(){
        int[] nums = new int[]{1,1,1,2,2,3};

        int[] result = main.topKFrequent(nums, 2);

        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testCombinationSum(){
        List<List<Integer>> list = main.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(list);
    }

    @Test
    public void testInorderTraversalWithIteration(){
        TreeNode node;
        List<Integer> list;
        //node = new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null));
        //list = main.inorderTraversalWithIteration(node);

        node = new TreeNode(3, new TreeNode(1, null, null), new TreeNode(2, null, null));

        list = main.inorderTraversalWithIteration(node);

        System.out.println(list);
    }
}