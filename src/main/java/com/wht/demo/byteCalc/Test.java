package com.wht.demo.byteCalc;

/**
 * desc.
 *
 * @author Wang Haitong
 * @date 2020/9/2 10:52
 */
public class Test {
    public static void main(String[] args) {
        //位运算取绝对值，核心思路是把符号位置为0

    }

    /**
     * 位运算乘10
     *
     * @param i 乘数
     * @return 乘数和10的乘积
     */
    public static int multiTen(int i) {
        //原理：i * 10 = i * 8 + i * 2;
        return (i << 3) + (i << 1);
    }

    /**
     * 位运算除以10
     *
     * @param i 被除数
     * @return 被除数除以10的商
     */
    public static int divideTen(int i) {
        return (i * 52429) >>> (16 + 3);
    }
}
