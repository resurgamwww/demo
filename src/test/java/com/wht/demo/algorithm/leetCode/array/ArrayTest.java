package com.wht.demo.algorithm.leetCode.array;


import org.junit.After;
import org.junit.Before;

/**
 * @author wanghtw
 * @date 2020/4/13 18:51
 */
public class ArrayTest {

    @org.junit.Test
    public void print() {
    }

    @org.junit.Test
    public void intersect() {
    }

    @org.junit.Test
    public void twoSum() {
    }

    @org.junit.Test
    public void rotate() {
    }

    @org.junit.Test
    public void isValidSudoku() {
    }

    int[][] matrix ;
    @Before
    public void before(){
        matrix =
                new int[][]{
                        {5, 1, 9, 11},
                        {2, 4, 8, 10},
                        {13, 3, 6, 7},
                        {15, 14, 12, 16}
                };

        matrix =
                new int[][]{
                        {1,2},
                        {3,4},
                };
    }

    @org.junit.Test
    public void testRotate() {


        printArray(matrix);

        Array.rotate(matrix);

        printArray(matrix);
    }

    private static void printArray(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int i : ints) {
                System.out.print(String.format("%-5d", i));
            }
            System.out.println();
        }
        System.out.println();
    }
}