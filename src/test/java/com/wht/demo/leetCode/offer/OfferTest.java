package com.wht.demo.leetCode.offer;

import com.google.gson.Gson;
import com.wht.demo.algorithm.util.RandomUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * desc.
 *
 * @author wht
 */
public class OfferTest {
    private Offer offer = new Offer();
    private Gson gson = new Gson();

    @Test
    public void reversePrint() {

        String s = "[7,2,9,3,0,8,0,1,4,0]";
        int[] inputs = gson.fromJson(s, int[].class);

        ListNode node = null;

        for (int i = inputs.length - 1; i >= 0; i--) {
            node = new ListNode(inputs[i], node);
        }

        ListNode test = node;
        while (test != null) {
            System.out.print(test.val + ",");
            test = test.next;
        }

        int[] result = offer.reversePrint(node);

        System.out.println(Arrays.toString(result));

    }

    @Test
    public void testBuildTree() {
        int[] pre = new int[]{3, 9, 20, 15, 7};
        int[] in = new int[]{9, 3, 15, 20, 7};

        TreeNode treeNode = offer.buildTree(pre, in);

        System.out.println(treeNode.val);
    }

    @Test
    public void testCQueue() {
        CQueue queue = new CQueue();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());


        class CallableWorker implements Callable<Integer> {
            @Override
            public Integer call() throws Exception {
                for (int j = 0; j < 200000; j++) {
                    if (Math.random() > 0.5) {
                        queue.appendTail(RandomUtil.uniform(20));
                    } else {
                        queue.deleteHead();
                    }
                }
                return 1;
            }
        }
        ArrayList<CallableWorker> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(new CallableWorker());
        }

        try {
            executor.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFib() {
        int i = offer.fib(3);
        System.out.println(i);
    }

    @Test
    public void testNumWays() {
        int i = offer.numWays(4);
        System.out.println(i);
    }

    @Test
    public void testMinArray() {
        int[] a = new int[]{1, 1};

        System.out.println(offer.minArray(a));
    }

    @Test
    public void testExist() {
        char[][] chars = gson.fromJson("[[A,B,C,E],[S,F,C,S],[A,D,E,E]]", char[][].class);
        String word = "ABCCED";

        boolean exist = offer.exist(chars, word);

        assert exist;

        chars = gson.fromJson("[[a,b],[c,d]]", chars.getClass());
        word = "abcd";

        exist = offer.exist(chars, word);

        assert !exist;

        chars = gson.fromJson("[[a,a]]", chars.getClass());
        word = "aa";

        exist = offer.exist(chars, word);

        assert exist;

    }

    @Test
    public void testMovingCount() {
        int i = offer.movingCount(2, 3, 1);
        assert i == 3;

        i = offer.movingCount(3, 1, 0);
        assert i == 1;
    }

    @Test
    public void testCuttingRope() {
        int n = 10;

        int i = offer.cuttingRope(n);

        System.out.println(i);
    }

    @Test
    public void test() {

    }

    @Test
    public void testMyPow() {
        System.out.println(offer.myPow(2, -2));
        //System.out.println(offer.myPow(2.1, 3));
        //System.out.println(offer.myPow(2, 10));
    }

    @Test
    public void testToBinaryString() {
        System.out.println(offer.toBinaryString(4));
    }

    @Test
    public void testIsMatch() {
        boolean res = false;

        res = offer.isMatch("mississippi", "mis*is*p*.");
        assert !res;

        res = offer.isMatch("abce", "");
        assert !res;

        res = offer.isMatch("abce", "a");
        assert !res;

        res = offer.isMatch("abce", "abcd");
        assert !res;

        res = offer.isMatch("abcd", "abcd");
        assert res;

        res = offer.isMatch("abce", ".*");
        assert res;

        res = offer.isMatch("abcd", ".*cd");
        assert res;

        res = offer.isMatch("abce", ".*cd");
        assert !res;

        res = offer.isMatch("qaabcd", "qa.*cd");
        assert res;

        res = offer.isMatch("aab", "c*a*b");
        assert res;

        res = offer.isMatch("ab", ".*c");
        assert !res;
    }

    @Test
    public void testIsNumber() {
        Timer timer = new Timer();

        assert !offer.isNumber("e9");
        assert offer.isNumber(" 9 ");
    }

    @Test
    public void testExchange() {
        System.out.println(Arrays.toString(offer.exchange(new int[]{2, 16, 3, 5, 13, 1, 16, 1, 12, 18, 11, 8, 11, 11, 5, 1})));
    }

    @Test
    public void testMergeTwoLists() {
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(6)));
        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(4)));

        ListNode node = offer.mergeTwoLists(l1, l2);

        int last = -1;
        while (node != null) {
            assert node.val > last;
            last = node.val;
            node = node.next;
        }
    }

    @Test
    public void testSpiralOrder() {
        int[][] ints = gson.fromJson("[[7],[9],[6]]", int[][].class);
        System.out.println(Arrays.toString(offer.spiralOrder(ints)));
    }


    @Test
    public void testPathSum() {
        String s = "5,4,8,11,null,13,4,7,2,null,null,5,1";
        String[] split = s.split(",");
        //TreeNode treeNode = buildTreeFormStr(split);
        TreeNode treeNode = new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, new TreeNode(5), new TreeNode(1))));

        List<List<Integer>> list = offer.pathSum(treeNode, 22);

        System.out.println(list);
    }

    @Test
    public void testSerialize() {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String s = offer.serialize(treeNode);
        System.out.println(s);

        System.out.println(s.substring(1, s.length() - 1));

        TreeNode node = offer.deserialize("[]");

        System.out.println(offer.serialize(node));
    }

    @Test
    public void testPermutation() {
        System.out.println(Arrays.toString(offer.permutation("abc")));
    }

    @Test
    public void testMajorityElement() {
        int[] ints = gson.fromJson("[1,2,3,2,2,2,5,4,2]", int[].class);
        int i = offer.majorityElement(ints);
        System.out.println(i);
    }

    @Test
    public void testGetLeastNumbers() {
        int[] ints = {0, 0, 0, 2, 0, 5};
        int[] res = offer.getLeastNumbers(ints, 0);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testMedianFinder() {
        MedianFinder a = new MedianFinder();

        a.addNum(1);
        a.addNum(2);
        System.out.println(a.findMedian());
        a.addNum(3);
        System.out.println(a.findMedian());
    }

    @Test
    public void testTranslateNum() {
        int i = offer.translateNum(18580);
        System.out.println(i);
    }

    @Test
    public void findContinuousSequence() {
        int[][] res = offer.findContinuousSequence(9);
        for (int[] ints : res) {
            System.out.println(Arrays.toString(ints));
        }
    }

    @Test
    public void lastRemaining() {
        System.out.println(offer.lastRemaining(5, 3));
    }

    @Test
    public void minWindow() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("d:\\temp.txt"));
        String s1 = reader.readLine();
        String s2 = reader.readLine();

        long l = System.currentTimeMillis();
        //System.out.println();
        offer.minWindow(s1, s2);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void main() {


        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }

        //String s = "     sdfsd   ";
        //s = s.trim();
        //System.out.println(s);
        //System.out.println(Pattern.matches(".*cd", "abce"));

        //int a = 2;
        //
        //int base = 1000000007;
        //int rem = 3;
        ////因为要对1000000007取余,所以先计算3 ^ a -1 次方
        //for (int i = a; i > 0; i /= 2) {
        //    rem = (rem * (rem % base)) % base;
        //    if (i % 2 == 1) {
        //        rem = (3 * (rem % base)) % base;
        //    }
        //}
        //
        //System.out.println(rem == Math.pow(3, a));


        //SpinLock lock = new SpinLock();
        //lock.lock();
        //System.out.println("第一次锁住");
        //lock.lock();
        //System.out.println("第二次锁住");
        //lock.unlock();
        //lock.unlock();
    }

    class SpinLock {

        private AtomicReference<Thread> owner = new AtomicReference<>();

        public void lock() {

            Thread current = Thread.currentThread();

            while (!owner.compareAndSet(null, current)) {

            }

        }

        public void unlock() {

            Thread current = Thread.currentThread();

            owner.compareAndSet(current, null);

        }

    }
}