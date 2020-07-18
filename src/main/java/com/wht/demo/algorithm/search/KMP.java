package com.wht.demo.algorithm.search;

import com.wht.demo.algorithm.util.Util;

/**
 * KMP算法
 * <p>
 * 不管是用DFA还是PMT实现，关键点都在于找到最大公共前缀，只是在PMT实现方式中，前置处理只做了查找最大公共前缀；在DFA实现方式中，巧妙地构造了状态机，其中的重启状态如果用数组存下来就相当于PMT中的前缀表。相比PMT，DFA在前置处理中利用重启状态直接构造好了状态机，这样实际搜索时就不用再比较了。
 * <p>
 * 考虑到KMP的使用场景，通常是对流输入等不适合使用缓存的字符串进行搜索。这种场景下PMT的实现在搜索时还需要再做比较，相对于DFA直接获得要跳转到的状态，性能肯定是要略逊一筹。不过使用DFA实现时也需要注意字典大小，为了应对特殊字符，字典可能较大，可能会有一定的内存浪费。
 * <p>
 * 综合来说，普通场景下直接暴力匹配就可以了，流输入等场景使用DFA实现的KMP算法。
 *
 * @author wht
 */
public class KMP {

    private final String pat;

    private final int[][] dfa;

    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        int r = 256;
        dfa = new int[r][m];

        //构造DFA
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {

            //复制失败时的值
            for (int c = 0; c < r; c++) {
                dfa[c][j] = dfa[c][x];
            }

            //设置成功时的值
            dfa[pat.charAt(j)][j] = j + 1;


            //更新重启状态
            x = dfa[pat.charAt(j)][x];
        }

        print();
    }

    public void print() {
        Util.print(dfa[(int) 'a']);
        Util.print(dfa[(int) 'b']);
        Util.print(dfa[(int) 'c']);
    }

    public int search(String txt) {
        int i, j, n = txt.length(), m = pat.length();

        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }

        if (j == m) {
            return i - m;
        } else {
            return -1;
        }
    }
}
