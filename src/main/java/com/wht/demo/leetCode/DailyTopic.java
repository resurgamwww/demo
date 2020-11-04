package com.wht.demo.leetCode;

import com.wht.demo.leetCode.offer.ListNode;
import com.wht.demo.leetCode.offer.TreeNode;
import org.checkerframework.common.value.qual.IntVal;
import org.springframework.core.annotation.MergedAnnotationPredicates;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author wanghtw
 * @date 2019/11/25 22:52
 */
public class DailyTopic {
    public static void main(String[] args) {
        //HashMap<Integer, Integer> map = new HashMap<>();
        //Integer lastIndex = map.get(22);
        //System.out.println(lastIndex);

        String a = "123c";
        String def = "def";
        String b = "123";
        String cdef = "cdef";


        //s.intern();
        String s1 = "123cdef";
        s1.intern();
        String s = a + def;


        //false
        System.out.println(s == s1);
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
        for (int i = 0; i < nums.length - 1; i++) {
            result = nums[i] ^ result;

        }

        return result;
    }

    /**
     * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
     * <p>
     * https://leetcode-cn.com/problems/repeated-substring-pattern/
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        if (s == null || s.length() <= 1 || s.length() % 2 != 0) {
            return false;
        }

        //KMP算法，构建一个DFA
        //如果

        int state = 0;
        int last = 0;
        int[][] dfa = new int[128][s.length() / 2];

        //先过一遍，每次状态+1，如果发现当前字符等于首字符，记录当前位置，开始尝试从头沿着DFA走，
        for (int i = 0; i < s.length() / 2; i++) {

        }

        return true;
    }

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param moves
     * @return
     */
    public boolean judgeCircle(String moves) {
        int i = 0, j = 0;
        for (byte b : moves.getBytes()) {
            switch (b) {
                case 'R':
                    j += 1;
                    break;
                case 'L':
                    j -= 1;
                    break;
                case 'U':
                    i -= 1;
                    break;
                case 'D':
                    i += 1;
                    break;
                default:
                    return false;
            }
        }

        return i == 0 && j == 0;
    }

    public String reverseWords(String s) {
        char[] chars = new char[s.length()];

        int begin = 0;
        boolean isLastBlank = false;
        for (int i = 0; i < chars.length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (!isLastBlank) {
                    //上一个不是空，当前为空，则从begin到i-1位置是一个单词，复制进去
                    for (int j = begin; j < i; j++) {
                        chars[j] = s.charAt(i - 1 - (j - begin));
                    }

                    isLastBlank = true;
                }

                chars[i] = ' ';
            } else {
                if (isLastBlank) begin = i;

                isLastBlank = false;
            }

        }
        if (!isLastBlank) {
            //上一个不是空，当前为空，则从begin到i-1位置是一个单词，复制进去
            for (int j = begin; j < s.length(); j++) {
                chars[j] = s.charAt(s.length() - 1 - (j - begin));
            }

        }
        return new String(chars);
    }

    boolean[] vis;
    int nums = 0;
    List<List<Integer>> rooms;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        vis = new boolean[rooms.size()];
        this.rooms = rooms;

        dfsForCanVisitAllRooms(0);

        return nums == rooms.size();
    }

    public void dfsForCanVisitAllRooms(int room) {
        if (vis[room]) return;

        vis[room] = true;
        nums++;

        rooms.get(room).forEach(this::dfsForCanVisitAllRooms);

    }

    List<String> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        //dfs，用字符串保存路径
        if (root == null) {
            return list;
        }

        dfs(new StringBuilder(), root);

        return list;
    }

    public void dfs(StringBuilder sb, TreeNode node) {
        if (node == null) {
            return;
        }

        StringBuilder builder = new StringBuilder(sb);
        builder.append(node.val);

        if (node.left == null && node.right == null) {
            //叶子节点则提交
            list.add(builder.toString());
        } else {
            builder.append("->");

            dfs(builder, node.left);
            dfs(builder, node.right);
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp = 0;

        int i1 = l1.val;


        return null;
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        //todo wanght 2020/9/7 10:27 做优化
        //1. 用堆的时间复杂度是O(NlogN)。用Java中自带的堆会有一些额外的开销，因为不支持固定长度，需要自己手动remove，优化思路是自己实现一个堆，在其中记录最大和最小值，放之前直接指定size，放之前比较一下即可。
        //2. 用快排的思路，只对最大的那部分排序即可。平均时间复杂度是O(N)，但是缺点是排序时需要有所有的结果集。

        //首先统计出现的频率
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        return topKFrequentWithHeap(k, map);
    }

    private int[] topKFrequentWithHeap(int k, HashMap<Integer, Integer> map) {
        //对频率排序，利用Java中的优先级队列，默认是个小顶堆
        //用小顶堆实现大顶堆，当堆的size < k时，放进去，当size >= k时，放进去之后remove掉头部，最后返回队列的逆序即可
        PriorityQueue<int[]> queue = getTopKQueueWithHeap(k, map);

        int[] result = new int[k];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = queue.remove()[0];
        }

        return result;
    }

    private PriorityQueue<int[]> getTopKQueueWithHeap(int k, HashMap<Integer, Integer> map) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            if (queue.size() >= k) {
                if (queue.peek()[1] >= value) {
                    continue;
                } else {
                    queue.remove();
                }
            }
            queue.add(new int[]{key, value});
        }
        return queue;
    }

    private int[] topKFrequentWithPartition(int k, HashMap<Integer, Integer> map) {
        //利用分治法，类似快排，但是只需要对较大的那部分排序
        int[] result = new int[k];


        return result;
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        ArrayList<List<Integer>> list = new ArrayList<>();

        dfsForCombine(n, k, 1, new ArrayDeque<>(), list);
        return list;
    }

    public void dfsForCombine(int n, int k, int begin, Deque<Integer> path, ArrayList<List<Integer>> list) {
        if (path.size() == k) {
            list.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i <= n - k + 1; i++) {
            path.add(i);
            dfsForCombine(n, k, i + 1, path, list);
            path.removeLast();
        }
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     * <p>
     * 说明：
     * <p>
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1：
     * <p>
     * 输入：candidates = [2,3,6,7], target = 7,
     * 所求解集为：
     * [
     * [7],
     * [2,2,3]
     * ]
     * 示例 2：
     * <p>
     * 输入：candidates = [2,3,5], target = 8,
     * 所求解集为：
     * [
     * [2,2,2,2],
     * [2,3,3],
     * [3,5]
     * ]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= candidates.length <= 30
     * 1 <= candidates[i] <= 200
     * candidate 中的每个元素都是独一无二的。
     * 1 <= target <= 500
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        //类似上一道题，但是这道的区别在于i本身可以重复，递归时，如果总和=target，就加入结果集，如果遍历到尾部还不满足或者总和>target，就结束循环。
        //反向思考，f(n) = candidates[i] + f(n - candidates[i])，因为不能有重复的，表示i可以从0开始不断增加，并且i不会再减小

        ArrayList<List<Integer>> list = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayDeque<Integer>(), list);
        return list;
    }

    public void dfs(int[] candidates, int target, int begin, Deque<Integer> path, ArrayList<List<Integer>> list) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            list.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (target < candidates[i]) {
                break;
            }
            path.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, path, list);
            path.removeLast();
        }
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * @param root 根节点
     * @return 中序遍历
     */
    public List<Integer> inorderTraversalWithRecursion(TreeNode root) {
        //中序遍历:先看左子节点，然后看根节点，最后看右子节点。递归很简单，就不写了。尝试下递归。
        ArrayList<Integer> list = new ArrayList<>();

        inorderTraversalWithRecursion(root, list);
        return list;


    }

    public void inorderTraversalWithRecursion(TreeNode node, List<Integer> list) {

        if (node == null) {
            return;
        }

        if (node.left != null) {
            inorderTraversalWithRecursion(node.left, list);
        }

        list.add(node.val);

        if (node.right != null) {
            inorderTraversalWithRecursion(node.right, list);
        }
    }

    public List<Integer> inorderTraversalWithIteration(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;

        label:
        while (node != null) {

            if (node.left != null) {
                stack.add(node);
                node = node.left;
                continue;
            }

            list.add(node.val);

            if (node.right != null) {
                node = node.right;
                continue;
            }


            while (!stack.empty()) {
                node = stack.pop();
                list.add(node.val);
                if (node.right != null) {
                    node = node.right;
                    continue label;
                }
            }
            break;
        }

        return list;
    }

    /**
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * <p>
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：arr = [1,2,2,1,1,3]
     * 输出：true
     * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
     * 示例 2：
     * <p>
     * 输入：arr = [1,2]
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }

        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Map<Integer, Integer> map = list.parallelStream().collect(Collectors.toMap(w -> w, w -> 1, Integer::sum));

        HashMap<Integer, Integer> result = new HashMap<>(map.size());

        for (Integer i : map.values()) {
            if (result.containsKey(i)) {
                return false;
            } else {
                result.put(i, 1);
            }
        }

        return true;
    }


    int sum = 0;

    /**
     * 每个节点是一个数字，从根节点到叶子节点的路径会组成一个数字，求所有数字之和
     * <p>
     * 解题思路：DFS OR BFS，然后将所有数字相加即可
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode node, int num) {
        num = (num << 3) + (num << 1) + node.val;

        if (node.left != null) {
            dfs(node.left, num);
        }
        if (node.right != null) {
            dfs(node.right, num);
        }
        if (node.left == null && node.right == null) {
            sum += num;
        }
    }

    /**
     * 有效的山脉数组
     * <p>
     * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
     * <p>
     * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
     * <p>
     * A.length >= 3
     * 在 0 < i < A.length - 1 条件下，存在 i 使得：
     * A[0] < A[1] < ... A[i-1] < A[i]
     * A[i] > A[i+1] > ... > A[A.length - 1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-mountain-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public boolean validMountainArray(int[] A) {
        //其实就是要求必须有一个极大值A[i]，且0<i<A.length() -1

        //所以思路也很简单，先判断每个值是不是比前一个值大，如果是的话就继续，直到找到一个极大值，如果到数组尾部还没找到，返回false，之后判断每个值是不是比前一个值小，否则返回false，如果到了数组尾部，返回true

        int length = A.length;
        //处理特殊情况
        if (length < 3) {
            return false;
        }
        if (A[0] > A[1]) {
            return false;
        }
        if (A[length - 1] > A[length - 2]) {
            return false;
        }

        int i;
        boolean down = false;
        for (i = 1; i < length - 1; i++) {
            if (A[i] > A[i + 1]) {
                down = true;
                break;
            }
        }
        if (down) {
            for (int j = i + 1; j < length - 1; j++) {
                if (A[j] >= A[j - 1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * <p>
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0 || (intervals.length == 1 && intervals[0].length == 0)) {
            return new int[][]{newInterval};
        }
        if (newInterval == null || newInterval.length == 0) {
            return intervals;
        }

        ArrayList<int[]> list = new ArrayList<>(intervals.length + 1);

        boolean flag = false;
        for (int i = 0, intervalsLength = intervals.length; i < intervalsLength; i++) {
            int[] interval = intervals[i];
            //设当前区间为A，新区间为B，有几种情况：
            //1. A在B左侧，无交集，continue；
            //2. B在A左侧，无交集，将B插入A之前，break；
            //3. A与B有交集：A的左边界小于B的右边界，但是A的右边界大于等于B的左边界，A在左，B在右；
            //4. A与B有交集：B的左边界小于A的右边界，但是B的右边界大于等于A的左边界，B在左，A在右；
            //5. A与B完全重合，不处理；
            //思路：为了方便扩容，弄一个新的List来存结果集
            //遍历原始集合，
            //A在B左侧
            if (flag){
                list.add(interval);
                continue;
            }

            if (interval[1] < newInterval[0]) {
                list.add(interval);
                continue;
            }
            //B在A左侧，插入
            if (interval[0] > newInterval[1]) {
                flag = true;
                list.add(newInterval);
                list.add(interval);
                continue;
            }
            //如果A与B完全重合
            if (interval[0] == newInterval[0] && interval[1] == newInterval[1]) {
                flag = true;
                list.add(interval);
                continue;
            }
            //如果A与B有交叉则合并，找到四个值中的最小值和最大值作为新的区间。
            int min = Math.min(interval[0], Math.min(interval[1], Math.min(newInterval[0], newInterval[1])));
            int max = Math.max(interval[0], Math.max(interval[1], Math.max(newInterval[0], newInterval[1])));
            newInterval[0] = min;
            newInterval[1] = max;
        }
        if (!flag) {
            list.add(newInterval);
        }
        return list.toArray(new int[0][]);
    }
}
