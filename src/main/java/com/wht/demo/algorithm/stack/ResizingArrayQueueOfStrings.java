package com.wht.demo.algorithm.stack;

import java.util.Arrays;

/**
 * P1.3.14
 *
 * @author wht
 * @date 2020/4/29 11:03
 */
public class ResizingArrayQueueOfStrings {
    private String[] elements;
    /**
     * 元素总个数
     */
    private int n = 0;

    public ResizingArrayQueueOfStrings() {
        this.elements = new String[8];
    }

    public ResizingArrayQueueOfStrings(int size) {
        this.elements = new String[size];
    }

    public boolean add(String s) {
        if (n == elements.length) {
            this.resize(elements.length * 2);
        }

        elements[n++] = s;

        return true;
    }

    public String remove(int i) {
        if (i >= 0 && i < n) {
            String temp = elements[i];
            elements[i] = null;
            return temp;
        }
        return null;
    }

    public boolean remove(String s) {
        for (int i = 0, elementsLength = elements.length; i < elementsLength; i++) {
            String element = elements[i];
            if (element.equals(s)) {
                elements[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * 扩容到两倍大小
     */
    private void resize(int newSize) {
        elements = Arrays.copyOf(elements, newSize);
    }
}
