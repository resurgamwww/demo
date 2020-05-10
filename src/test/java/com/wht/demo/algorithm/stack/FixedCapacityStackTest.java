package com.wht.demo.algorithm.stack;

import org.junit.Test;

import java.util.ArrayList;

public class FixedCapacityStackTest {

    @Test
    public void main() {

        FixedCapacityStack<String> stack = new FixedCapacityStack<>(20);

        stack.push("123");
        String pop = stack.pop();
        System.out.println(pop);

        Object[] strings = stack.popAll();

    }

    @Test
    public void testInt(){
        int i = 0;
        int a = 0;

        System.out.println(i == a);

        Integer i1 = Integer.valueOf(123);
        Integer a1 = Integer.valueOf(123);

        System.out.println(i1 == a1);

        char c = '1';

        char c1 = '1';

        System.out.println(c == c1);

        String s = String.valueOf(1);
        String s1 = String.valueOf(1);
        System.out.println(s == s1);
    }

    @Test
    public void test139(){

        System.out.println(true && false || true);

        ArrayList<String> list = new ArrayList<>(20);
        //System.out.println(list.size());
        //System.out.println(list.iterator());

        //String[] array = new String[20];
        //System.out.println(array.length);

        list.add("123");

        System.out.println(list);
    }
}