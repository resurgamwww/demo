package com.wht.demo.algorithm.sort;

import com.wht.demo.algorithm.util.RandomUtil;
import org.junit.*;
import org.junit.rules.TestName;

import java.util.Arrays;
import java.util.Random;

/**
 * @author wht
 * @date 2020/5/7 22:18
 */
public class SortTest {
    //保存方法的结果
    private Integer[] result;

    //保存原始数组
    private static Integer[] origin;
    //保存按递增排序的正确的数组结果
    private static Integer[] success;

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void beforeClass() {
        int length = new Random().nextInt(30);
        origin = new Integer[length];
        for (int i = 0; i < length; i++) {
            origin[i] = RandomUtil.uniform(i - 5, i + 5);
        }

        RandomUtil.shuffle(origin);

        success = Arrays.copyOf(origin, origin.length);
        Arrays.sort(success);

        System.out.println("before all:" + Arrays.toString(origin));
        System.out.println("expected:" + Arrays.toString(success));
    }

    @Before
    public void before() {
        result = Arrays.copyOf(origin, origin.length);
    }

    @After
    public void after() throws Exception {
        boolean flag = Arrays.equals(result, success);
        System.out.println(testName.getMethodName() + "：" + flag);
        if (!flag) {
            System.out.println(Arrays.toString(result));

        }
        assert flag;
    }

    @Test
    public void testMergeSort() {
        MergeSort.sort(result);
    }

    @Test
    public void testQuickSort() {
        QuickSort.sort(result);
    }

    @Test
    public void testInsertSort() {
        InsertSort.sort(result);
    }

    @Test
    public void testDumpSort() {
        HeapSort.sort(result);
    }

    @Test
    public void testBubbleSort() {
        BubbleSort.bubbleSort(result);
    }
}