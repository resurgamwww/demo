package com.wht.demo.leetCode.offer;

import org.springframework.lang.NonNull;

import java.util.*;

/**
 * 剑指Offer系列
 *
 * @author wht
 */
public class Offer {
    public static void main(String[] args) {
        Offer offer = new Offer();

        //String s = "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]";
        //
        //Gson gson = new Gson();
        //int[][] integers = gson.fromJson(s, int[][].class);
        //
        //boolean b = findNumberIn2DArray(new int[][]{{}}, 5);
        //System.out.println(b);

        String s = "We are happy.";
        System.out.println(offer.replaceSpace(s));
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findRepeatNumber(int[] nums) {
        int[] ints = new int[nums.length];
        for (int i : nums) {
            if (ints[i] != 0) {
                return i;
            } else {
                ints[i]++;
            }
        }
        //无重复数字
        return 0;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        //查找在哪一行
        for (int[] ints : matrix) {
            if (ints.length == 0) {
                continue;
            }
            if (ints[0] > target) {
                //因为是递增的，假如某一行的第一列已经大于目标，目标肯定不会存在于后续数组中
                return false;
            } else if (ints[0] == target) {
                //应对特殊情况
                return true;
            } else {
                if (ints[ints.length - 1] < target) {
                    continue;
                } else {
                    //二分查找
                    int temp, low = 0, high = ints.length - 1;
                    while (low <= high) {
                        temp = low + ((high - low) >> 1);
                        if (ints[temp] < target) {
                            low = temp + 1;
                        } else if (ints[temp] > target) {
                            high = temp - 1;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        //return s.replace(" ", "%20");

        char[] chars = s.toCharArray();

        //由于替换一次需要多插入两个元素，所以难点在于如何确定新char[]的长度，避免过短导致需要不断扩容，避免过长导致内存浪费。所以这里先循环一次，统计出空格的数量，以便精确的确定新数组的长度。
        int count = 0;
        for (char c : chars) {
            if (c == ' ') {
                count++;
            }
        }

        char[] newChars = new char[chars.length + count * 2];

        for (int i = 0, j = 0, charsLength = chars.length; i < charsLength; j++, i++) {
            char c = chars[i];
            if (c != ' ') {
                newChars[j] = c;
            } else {
                newChars[j] = '%';
                newChars[++j] = '2';
                newChars[++j] = '0';
            }
        }

        return new String(newChars);
    }


    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }

        int length = 8;
        int[] temp = new int[length];
        int i = -1;

        ListNode node = head;
        while (node != null) {
            if (i >= length - 1) {
                length *= 2;
                temp = Arrays.copyOf(temp, length);
            }

            temp[++i] = node.val;
            node = node.next;
        }

        int[] result = new int[i + 1];
        for (int j = 0; i >= 0 && j < result.length; j++, i--) {
            result[j] = temp[i];
        }

        return result;
    }


    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 原题链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // todo 这道题我用的解法耗时3ms，有个耗时1ms的，没有使用开始结束位置和map，只对前序和中序各用了一个index，没看懂，有时间再研究下那种解法。

        if (preorder == null || preorder.length == 0) {
            return null;
        }
        if (inorder == null || inorder.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : inorder) {
            map.put(i, count++);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    /**
     * 通过前序能知道根节点，通过根节点在中序中的位置，能知道左右子树的结点数量。
     * 找到左右子树的起始位置，再次找子树的前序中的第一个元素，能知道子树的根节点，进而通过在中序中的位置，知道根基欸但左右子树的起始位置。如此循环下去。
     * 假如开始位置和结束位置相等，说明当前结点是叶子结点。如果开始位置小于结束位置，说明当前子树中有多个元素。如果大于，说明没有结点。
     *
     * @param inBegin 当前树在中序遍历中的开始位置
     * @param inEnd   当前树在中序遍历中的结束位置
     * @param map
     * @return 返回当前树的根结点
     */
    private TreeNode buildTree(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd, Map<Integer, Integer> map) {

        if (inBegin > inEnd) {
            //没有结点
            return null;
        } else if (inBegin == inEnd) {
            //根结点
            return new TreeNode(inorder[inBegin]);
        } else {
            //有多个结点，继续找子树

            //先从前序遍历找到根节点
            int root = preorder[preBegin];
            TreeNode rootNode = new TreeNode(root);

            //根节点在中序遍历中的位置
            Integer index = map.get(root);

            //确定左右子树的数量，并确定在前序和中序中的位置
            int leftCount = index - inBegin;
            int rightCount = inEnd - index;


            //左子树的根节点
            rootNode.left = buildTree(preorder, preBegin + 1, preBegin + leftCount, inorder, inBegin, index - 1, map);

            //右子树的根节点
            rootNode.right = buildTree(preorder, preEnd - rightCount + 1, preEnd, inorder, index + 1, inEnd, map);

            return rootNode;
        }
    }

    /**
     * 计算斐波那契数列
     * 对1000000007取余
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        //f(n) = f(n-1) + f(n-2);
        switch (n) {
            case 0:
                return 0;
            case 1:
                return 1;
        }

        int mod = 1000000007;
        int sum = 0, a = 0, b = 1;

        for (int i = 2; i <= n; i++) {
            sum = ((a % mod) + (b % mod)) % mod;
            a = b;
            b = sum;
        }

        return sum;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
     * <p>
     * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numWays(int n) {
        //f(n) = f(n-1) + f(n-2);
        //吐槽一下，LeetCode上f(0)竟然要求输出1
        if (n < 2) {
            return 1;
        }

        int mod = 1000000007;
        int sum = 0, a = 1, b = 1;

        for (int i = 2; i <= n; i++) {
            sum = ((a % mod) + (b % mod)) % mod;
            a = b;
            b = sum;
        }

        return sum;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
     * 示例 1：
     * 输入：[3,4,5,1,2]
     * 输出：1
     * 示例 2：
     * 输入：[2,2,2,0,1]
     * 输出：0
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minArray(int[] numbers) {

        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        if (numbers[0] < numbers[numbers.length - 1]) {
            return numbers[0];
        }

        int left = 0, right = numbers.length - 1, mid = 0;

        while (left < right) {
            mid = left + ((right - left) >> 1);

            if (numbers[mid] > numbers[right]) {
                //往右边找
                left = mid + 1;
                continue;
            } else if (numbers[mid] < numbers[right]) {
                //往左边找
                right = mid;
                continue;
            } else {
                //相等，这时无法判断最小值到底在左还是在右

                //直接遍历
                int min = numbers[left];
                for (int i = left + 1; i <= right; i++) {
                    if (numbers[i] < min) {
                        min = numbers[i];
                    }
                }

                return min;
            }
        }

        return numbers[left];
    }

    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        char[] words = word.toCharArray();
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            if (board[i].length == 0) {
                continue;
            }

            for (int j = 0; j < board[i].length; j++) {
                if (dfsForExist(board, i, j, words, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board 矩阵
     * @param i     要搜索的行号
     * @param j     要搜索的列号
     * @param words 字符串
     * @param index 要搜索的字符下标
     * @return
     */
    private boolean dfsForExist(char[][] board, int i, int j, char[] words, int index) {
        //0.检查下标是否越界
        if (index >= words.length) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return false;
        }

        //1.检查元素本身是否在字符串开头
        if (words[index] != board[i][j]) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '/';

        //2.沿顺时针方向依次查找右下左上

        if (dfsForExist(board, i, j + 1, words, index + 1) ||
                dfsForExist(board, i + 1, j, words, index + 1) ||
                dfsForExist(board, i, j - 1, words, index + 1) ||
                dfsForExist(board, i - 1, j, words, index + 1)
        ) {
            return true;
        }

        board[i][j] = temp;
        return false;
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 2：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        //todo 但是其实感觉这道题可以分多种情况直接计算出能走的数量，因为只限制了位数和，根据k能直接算出哪些i，j符合要求，最左上角那块大的等腰直角三角形（[0,0],[k,0],[0,k]围成）能直接算出来数量，等差数列：（首项+末项）*项数/2，剩下的部分分几种情况，就是可能比较麻烦。

        this.m = m;
        this.n = n;
        this.k = k;
        array = new boolean[m][n];

        return dfsForMovingCount(0, 0);
    }

    int m, n, k;
    boolean[][] array;

    private int getDigitalSum(int i, int j) {
        int s = 0;


        while (i != 0) {
            s += i % 10;
            i /= 10;
        }

        while (j != 0) {
            s += j % 10;
            j /= 10;
        }

        return s;
    }

    private int dfsForMovingCount(int i, int j) {
        if (i >= m || j >= n || array[i][j] || (getDigitalSum(i, j) > k)) {
            return 0;
        }

        array[i][j] = true;

        return 1 + dfsForMovingCount(i, j + 1) + dfsForMovingCount(i + 1, j);
    }

    /**
     * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int a = n / 3;
        int mod = n % 3;

        int base = 1000000007;
        long result = 1;
        long rem = 3;
        //因为要对1000000007取余,所以先计算3 ^ a -1 次方
        for (int i = a - 1; i > 0; i /= 2) {
            if (i % 2 == 1) {
                result = (rem * result) % base;
            }
            rem = (rem * rem) % base;

        }


        switch (mod) {
            case 0:
                return (int) (result * 3 % base);
            case 1:
                return (int) (result * 4 % base);
            case 2:
                return (int) (result * 6 % base);
        }
        return 0;
    }

    /**
     * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 这题的描述有点问题，一开始说的是n是无符号数，但是测试用例中有负数
     */
    public int hammingWeight(int n) {
        //int count = 0;
        //while (n != 0) {
        //    count += n & 1;
        //    n = n >>> 1;
        //}
        //return count;
        return Integer.bitCount(n);
    }

    public String toBinaryString(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n >>= 1;
        }

        return sb.reverse().toString();
    }

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }

        double result = 1;
        double rem = x;
        int t = n;

        while (n != 0) {
            if ((n & 1) == 1) {
                result *= rem;
            }
            rem *= rem;
            n /= 2;
            System.out.println(Integer.toBinaryString(n));
        }

        return t > 0 ? result : 1.0 / result;
    }

    public int[] printNumbers(int n) {
        int max = n * 10 - 1;
        int[] array = new int[max];

        for (int i = 0; i < max; ) {
            array[i] = ++i;
        }
        return array;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }

        ListNode node = head;

        while (node != null) {
            if (node.next != null && node.next.val == val) {
                node.next = node.next.next;
                return head;
            } else {
                node = node.next;
            }
        }

        //不存在
        return head;
    }

    /**
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isMatch(String s, String p) {

        //1.可以用NFA，不过需要图的知识，等学会了再试这道题，目前先用其他思路
        //2.使用Pattern.matches(p, s);
        //3.todo 动态规划

        return false;
    }

    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int[][] dfa = new int[][]{
                //{0, 1, 2, 3, 4, 5, 6, 7, 8},//辅助，只是用来标记状态的横坐标
                {0, -1, 8, 8, -1, -1, -1, 8, 8},//blank
                {2, 2, 2, 3, 3, 7, 7, 7, -1},//digit
                {-1, -1, 5, 5, -1, -1, -1, -1, -1},//e
                {1, -1, -1, -1, -1, 6, -1, -1, -1},//sign
                {4, 4, 3, -1, -1, -1, -1, -1, -1},//dot
                {-1, -1, -1, -1, -1, -1, -1, -1, -1}//other
        };

        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            state = dfa[getIndex(s.charAt(i))][state];
            if (state == -1) {
                return false;
            }
        }

        switch (state) {
            case 2:
            case 3:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }


    }

    private final int getIndex(char c) {
        if (c == ' ') {
            return 0;
        } else if (c >= '0' && c <= '9') {
            //数字
            return 1;
        } else if (c == 'e' || c == 'E') {
            //表示科学计数法
            return 2;
        } else if (c == '+' || c == '-') {
            //符号标志位，
            return 3;
        } else if (c == '.') {
            //小数点
            return 4;
        } else {
            //其他
            return 5;
        }
    }

    /**
     * 反转该链表并输出反转后链表的头节点
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode last = null;
        ListNode node = head;
        ListNode next = null;

        while (true) {
            next = node.next;
            node.next = last;

            if (next == null) {
                break;
            }


            last = node;
            node = next;
        }

        return node;
    }

    /**
     * 合并两个已排序的链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 != null ? l1 : l2;
        return head.next;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {

        //slow指向下一个应为奇数的位置，即该位置当前为偶数，fast指向slow后的下一个奇数，当fast超出尾部后结束
        int fast = 0, slow = 0;

        while (fast < nums.length) {
            if (nums[slow] % 2 == 0) {
                //如果slow指向偶数，fast向后搜索
                while (fast < nums.length) {
                    if (nums[fast] % 2 == 1) {
                        //此时slow指向偶数，fast指向奇数，先交换两者指向的数字，然后将slow指向fast，fast+1
                        int temp = nums[slow];
                        nums[slow] = nums[fast];
                        nums[fast] = temp;

                        slow++;
                        fast++;
                    } else {
                        fast++;
                    }
                }
            } else {
                fast = ++slow + 1;
            }
        }

        return nums;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        LinkedList<ListNode> list = new LinkedList<>();

        ListNode node = head;
        while (node != null) {
            list.addLast(node);
            while (list.size() > k) {
                list.pop();
            }
            node = node.next;
        }

        return list.peek();
    }

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * <p>
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //搜索题，约等于字符串搜索
        //思路1：暴力匹配，最坏情况时间复杂度O(MN)
        //思路2：各先序遍历一次，生成两个数组，然后构造DFA，最后匹配，最坏情况时间复杂度O(M+N)，缺点是需要额外空间

        //这里偷下懒，直接暴力匹配
        if (A == null || B == null) {
            return false;
        }

        return searchForIsSubStructure(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    private boolean searchForIsSubStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        } else if (a == null || a.val != b.val) {
            return false;
        }

        return searchForIsSubStructure(a.left, b.left) && searchForIsSubStructure(a.right, b.right);

    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 判断一棵树是不是镜像的
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return search(root.left, root.right);
    }

    private boolean search(TreeNode a, TreeNode b) {
        if (a == null) {
            return b == null;
        }
        if (b == null || a.val != b.val) {
            return false;
        }

        return search(a.left, b.right) && search(a.right, b.left);
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }

        int height = matrix.length;
        int width = matrix[0].length;

        int length = width * height;

        //表示已经走过的圈数
        int round = 0;
        //指向结果数组的要插入的位置
        int index = 0;
        int[] res = new int[length];
        //坐标点
        int w, h;
        while (index < length) {
            //左上角 (round,round) 右上角 (round, width - 1 - round)
            //左下角 (height -1 -round, round) 右下角 (height -1 -round, width - 1 - round)
            w = width - 1 - round;
            h = height - 1 - round;
            //上边 从左上角到右上角
            for (int i = round; i <= w; i++) {
                res[index++] = matrix[round][i];
            }
            //右边 从右上到右下
            for (int i = round + 1; i < h; i++) {
                res[index++] = matrix[i][w];
            }
            //下边 从右下到左下，防止高度为奇数时重复扫描
            if (h > round) {
                for (int i = w; i >= round; i--) {
                    res[index++] = matrix[h][i];
                }
            }

            //左边 从左下到左上 防止宽度为奇数时重复扫描
            if (w > round) {
                for (int i = height - 2 - round; i > round; i--) {
                    res[index++] = matrix[i][round];
                }
            }


            //一圈走完，圈数+1
            round++;

        }
        return res;
    }

    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        for (int value : pushed) {
            stack.push(value);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        return stack.isEmpty();
    }

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     */
    public int[] levelOrder(TreeNode root) {
        //bfs

        if (root == null) {
            return new int[0];
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.pop();

            res.add(node.val);

            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }

        int[] ints = new int[res.size()];
        for (int i = 0, resSize = res.size(); i < resSize; i++) {
            ints[i] = res.get(i);
        }

        return ints;
    }

    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        ArrayList<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list;
        TreeNode node;
        boolean left = true;
        while (!queue.isEmpty()) {

            list = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                node = queue.pop();

                if (left) {
                    list.add(node.val);
                } else {
                    list.addFirst(node.val);
                }

                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            left = !left;
            res.add(list);
        }

        return res;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同
     */
    public boolean verifyPostorder(int[] postorder) {
        //左->右->根结点
        //todo
        return false;
    }

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return null;
        }

        search(root, sum);
        return res;
    }

    private void search(TreeNode node, int sum) {
        //记录当前结点路径
        //当node.value = sum时成功，记录路径，返回
        //否则向下寻找 sum - node.value
        //这道题没说会不会有负数，所以就算node.value > sum，还是要继续找，只有当两值相等才能停下。

        if (node == null) return;
        path.add(node.val);
        sum -= node.val;
        if (sum == 0 && node.left == null && node.right == null) {
            res.add(new LinkedList<>(path));
        }

        search(node.left, sum);
        search(node.right, sum);

        path.removeLast();
    }

    /**
     * 复杂链表的复制
     * 这里边的结点多了一个指针，指向随机结点
     */
    public RandomNode copyRandomList(RandomNode head) {
        //如果只有一个结点，那么遍历链表复制即可，现在加入随机结点后，相当于变成了一个图

        return dfs(head);
    }

    HashMap<RandomNode, RandomNode> randomMap = new HashMap<>();

    private RandomNode dfs(RandomNode randomNode) {
        if (randomNode == null) {
            return null;
        }

        //未创建则创建，已创建的对象放到map中存起来，要用时直接取，key为原始对象，value为副本
        RandomNode copy = randomMap.get(randomNode);
        if (copy == null) {
            copy = new RandomNode(randomNode.val);
            randomMap.put(randomNode, copy);
            copy.next = dfs(randomNode.next);
            copy.random = dfs(randomNode.random);
        }

        return copy;
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        dfs(root);

        head.left = pre;
        pre.right = head;

        return head;
    }

    Node pre, head;

    private void dfs(Node cur) {
        if (cur == null) {
            return;
        }

        dfs(cur.left);

        if (pre == null) {
            head = cur;
        } else {
            pre.right = cur;
        }

        cur.left = pre;
        pre = cur;

        dfs(cur.right);

    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        //先不可考虑多余的null了，这题没限制输出的格式，只要求两个函数可逆
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder("[");
        while (!queue.isEmpty()) {

            for (int i = queue.size() - 1; i >= 0; i--) {
                TreeNode node = queue.pop();
                if (node == null) {
                    sb.append("null");
                } else {
                    sb.append(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
                sb.append(",");
            }


        }

        sb.replace(sb.length() - 1, sb.length(), "]");

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() <= 2) {
            return null;
        }

        String[] split = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty()) {

            for (int i1 = queue.size() - 1; i1 >= 0; i1--) {
                TreeNode node = queue.pop();

                if (!split[i].equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(split[i]));
                    queue.add(node.left);
                }
                i++;
                if (!split[i].equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(split[i]));
                    queue.add(node.right);
                }
                i++;
            }
        }
        return root;
    }

    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。排列顺序任意，但是不能有重复元素
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        list = new ArrayList<>((s.length() * (s.length() + 1)) >> 1);
        permutation(s.toCharArray(), 0, s.length() - 1);

        return list.toArray(new String[0]);
    }

    private ArrayList<String> list;

    private void permutation(char[] chars, int from, int to) {
        if (from == to) {
            list.add(new String(chars));
        } else {
            HashSet<Character> set = new HashSet<>();
            for (int i = from; i <= to; i++) {
                if (set.contains(chars[i])) continue;
                set.add(chars[i]);
                swap(chars, i, from);
                permutation(chars, from + 1, to);
                swap(chars, i, from);
            }
        }
    }

    public static <T> void swap(@NonNull char[] a, int i, int j) {
        char v = a[i];
        a[i] = a[j];
        a[j] = v;
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
        int threshold = nums.length >> 1;

        HashMap<Integer, Integer> map = new HashMap<>(threshold + 1);
        for (int i : nums) {
            Integer count = map.get(i);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            if (count > threshold) {
                return i;
            }
            map.put(i, count);
        }

        //目标值在上边的循环中就应该已经返回了，不应该走到这里
        return -1;
    }

    /**
     * 找出最小的n个数
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        //todo 快排实现
        if (arr == null || k == 0) {
            return new int[0];
        }
        //小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);

        for (int i : arr) {
            //if (queue.size() >= k) {
            //    queue.poll();
            //}

            queue.add(i);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.remove();
        }
        return res;
    }

    /**
     * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * 要求时间复杂度为O(n)。
     */
    public int maxSubArray(int[] nums) {
        //动态规划,f(n) = f(n-1) + n ，如果f(n)小于0，说明当前子数组的和的贡献为负，不带上反而更好，那么f(n + 1) =  array[n+1];
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int last = nums[0];
        int max = last;
        for (int i = 1, numsLength = nums.length; i < numsLength; i++) {
            last = Math.max(last, 0) + nums[i];
            max = Math.max(max, last);
        }

        return max;
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>(s.length());
        int left = -1, res = 0;
        for (int i = 0, charArrayLength = s.length(); i < charArrayLength; i++) {
            Integer lastIndex = map.get(s.charAt(i));
            if (lastIndex != null) {
                left = Math.max(lastIndex, left);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - left);
        }
        return res;
    }

    /**
     * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }

        int beforeLast = 1, last = 1, cur = 1;

        String s = String.valueOf(num);
        for (int i = 1, length = s.length(); i < length; i++) {
            String tmp = s.substring(i - 1, i + 1);
            if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) {
                cur = beforeLast + last;
            } else {
                cur = last;
            }

            beforeLast = last;
            last = cur;
        }
        return cur;
    }

    /**
     * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maxValue(int[][] grid) {
        //动态规划
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     */
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }

        int[] table = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (table[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    /**
     * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     */
    public int nthUglyNumber(int n) {
        //动态规划

        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) a++;
            if (dp[i] == n3) b++;
            if (dp[i] == n5) c++;

        }
        return dp[n - 1];
    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     */
    public int reversePairs(int[] nums) {
        //todo
        return 0;
    }

    /**
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     */
    public int[] singleNumbers(int[] nums) {
        //如果只有一个只出现一次的数字，直接整个异或即可。但是这里有两个，思路就是把数组拆成两半，让两个数字刚好分散在两边，如何做到？关键是两个数字异或之后，由于这两个数字必定不相等，所以结果必定不为0，而二进制中为1的那位，就表示这位上两个数字不同，所以用这位即可把两个数字分开。然后两个数组各自异或即可。

        int sum = 0;
        for (int i : nums) {
            sum ^= i;
        }

        int first = 1;
        while ((sum & first) == 0) {
            first <<= 1;
        }

        int[] res = new int[2];
        for (int i : nums) {
            if ((i & first) == 0) {
                res[0] ^= i;
            }
            //进一步优化，由于sum是两个数字异或的结果，只要知道其中一个数字，并再次异或，就能得到另一个了。所以这里不用再挨个去异或。另外循环中数组取值操作的性能损耗是否能抵消掉new一个临时变量的开销？
            //else {
            //    res[1] ^= i;
            //}
        }
        res[1] = res[0] & sum;
        return res;
    }

    /**
     * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean patternMatching(String pattern, String value) {
        //todo 学习下图的算法和正则表达式
        return false;
    }

    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     */
    public int kthLargest(TreeNode root, int k) {
        this.kOfKthLargest = k;
        dfs(root);
        return kthLargest;
    }

    private int kthLargest;
    private int kOfKthLargest;

    public void dfs(TreeNode node) {
        if (node == null || k == 0) {
            return;
        }

        dfs(node.left);

        if (--k == 0) {
            kthLargest = node.val;
            return;
        }

        dfs(node.right);
    }

    /**
     * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
     */
    public int[] twoSum(int[] nums, int target) {
        //对撞指针，两个指针，分别放在最低位和最高位，向中间搜索
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        int i = 0, j = nums.length - 1;
        int[] res = new int[2];
        int temp;
        while (i < j) {
            temp = nums[i] + nums[j] - target;
            if (temp == 0) {
                res[0] = nums[i];
                res[1] = nums[j];
                return res;
            } else if (temp > 0) {
                j--;
            } else {
                i++;
            }
        }
        return res;
    }

    /**
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * <p>
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] findContinuousSequence(int target) {
        //滑动窗口法，类似上一题，两个指针指向一段区间的开始和结束位置
        //target >= 3
        int i = 1, j = 2, sum = 3;

        int[] temp;
        ArrayList<int[]> res = new ArrayList<>(target);
        while (i < j) {
            if (sum == target) {
                temp = new int[j - i + 1];
                for (int l = 0, t = i; l < temp.length; l++, t++) {
                    temp[l] = t;
                }
                res.add(temp);
                sum -= i;
                i++;
            } else if (sum > target) {
                sum -= i;
                i++;
            } else {
                j++;
                sum += j;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int lastRemaining(int n, int m) {

        int res = 0;
        int count = 1;
        while (count <= n) {
            res = (m + res) % count;
            count++;
        }
        return res;
    }

    public int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (f(n - 1, m) + m) % n;
    }

    //t中各个字符的数量
    HashMap<Character, Integer> tMap;
    //当前窗口下各个字符的数量
    HashMap<Character, Integer> map;

    int[] tArray = new int[128];
    int[] sArray = new int[128];

    public String minWindow(String s, String t) {
        if (t == null || s == null || s.length() < t.length()) {
            return "";
        }

        //滑动窗口，维护两个指针，记录下窗口内各字符的数量，如果各个字符的数量都大于或者等于t内各个字符的数量，那么说明当前窗口的子字符串满足条件。如果字符串数量有大于t内字符数量的，说明有多余的字符，这里可以额外维护下各个字符的最小位置，如果多余的字符刚好在最左边，左侧指针可以直接移动到下一个位置。如果各个字符串的数量都相等，则左边不可再收缩，只能向右边扩张。

        for (int i = 0, length = t.length(); i < length; i++) {
            char c = t.charAt(i);
            tArray[c] += 1;
        }


        int left = 0, right = 0;

        int minL = -1;
        int sLength = s.length();
        int minR = sLength + 1;

        //优化的重点就在于这个count，通过记录需要的字符长度来避免每次扫描时要遍历整个目标表来比对次数
        int count = 0;
        char c;
        while (right < sLength) {
            c = s.charAt(right);
            if (tArray[c] > 0) {
                sArray[c] += 1;
                if (tArray[c] >= sArray[c]){
                    count ++;
                }
            }

            while (count == t.length()) {

                c = s.charAt(left);

                if (tArray[c] > 0) {
                    if (tArray[c] >= sArray[c]){
                        count --;
                    }
                    sArray[c] -= 1;
                }
                if (right - left < minR - minL) {
                    minL = left;
                    minR = right;
                }

                left++;
            }
            right++;
        }

        return minL == -1 ? "" : s.substring(minL, minR + 1);
    }


    public String minWindowOld(String s, String t) {
        if (t == null || s == null || s.length() < t.length()) {
            return "";
        }

        //滑动窗口，维护两个指针，记录下窗口内各字符的数量，如果各个字符的数量都大于或者等于t内各个字符的数量，那么说明当前窗口的子字符串满足条件。如果字符串数量有大于t内字符数量的，说明有多余的字符，这里可以额外维护下各个字符的最小位置，如果多余的字符刚好在最左边，左侧指针可以直接移动到下一个位置。如果各个字符串的数量都相等，则左边不可再收缩，只能向右边扩张。

        tMap = new HashMap<>(t.length());
        for (int i = 0, length = t.length(); i < length; i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        map = new HashMap<>(tMap.size());


        int left = 0, right = 0;

        int minL = -1;
        int sLength = s.length();
        int minR = sLength + 1;

        char c;
        while (right < sLength) {
            c = s.charAt(right);
            if (tMap.containsKey(c)) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            while (checkOld() && left <= right) {
                if (right - left < minR - minL) {
                    minL = left;
                    minR = right;

                }
                if (tMap.containsKey(s.charAt(left))) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                }
                left++;
            }
            right++;
        }

        return minL == -1 ? "" : s.substring(minL, minR + 1);
    }

    private boolean checkOld() {
        if (tMap.size() > map.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            if (map.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路：两个栈s1，s2，插入数据时压入s1，要弹出数据时，将所有数据都从s1弹出并依次压入s2。多线程的情况下，将数据从s1转移到s2的过程时必须堵塞插入和删除方法，否则会出现并发问题，如果对push的顺序没有要求的话适合使用读写锁。
 */
class CQueue {

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (s2.isEmpty()) {
            if (s1.isEmpty()) {
                return -1;
            }
            move();
        }
        return s2.pop();
    }

    private void move() {
        //将元素从s1搬到s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
class MinStack {

    Stack<Integer> a;
    Stack<Integer> b;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(int x) {
        a.push(x);
        if (b.isEmpty() || b.peek() >= x) {
            b.push(x);
        }
    }

    public void pop() {
        if (a.isEmpty()) {
            return;
        }
        if (a.pop().equals(b.peek())) {
            b.pop();
        }
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        return b.peek();
    }
}

class RandomNode {
    int val;
    RandomNode next;
    RandomNode random;

    public RandomNode(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

class MedianFinder {

    //小顶堆保存较大的一半
    private PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    //大顶堆保存较小的一半，这样个堆的堆顶就最靠近中位数
    private PriorityQueue<Integer> maxQueue = new PriorityQueue<>(((o1, o2) -> -o1.compareTo(o2)));

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (minQueue.size() == maxQueue.size()) {
            maxQueue.add(num);
            minQueue.add(maxQueue.poll());
        } else {
            minQueue.add(num);
            maxQueue.add(minQueue.poll());
        }
    }

    public double findMedian() {
        return maxQueue.size() != minQueue.size() ? minQueue.peek() : (maxQueue.peek() + minQueue.peek()) / 2.0;
    }
}
