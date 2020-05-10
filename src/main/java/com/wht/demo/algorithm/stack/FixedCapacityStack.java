package com.wht.demo.algorithm.stack;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FixedCapacityStack<T> {
    private T[] a;
    private int n;

    public FixedCapacityStack(int cap) {
        a = (T[]) new Object[cap];
    }

    public FixedCapacityStack(Class clazz, int cap) {
        a = (T[]) Array.newInstance(clazz, cap);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(T item) {
        a[n++] = item;
    }

    public T pop() {
        return a[--n];
    }

    public T[] popAll() {
        int temp = n;
        n = 0;
        return Arrays.copyOfRange(a, 0, temp);
    }
}
