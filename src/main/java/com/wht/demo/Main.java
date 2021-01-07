package com.wht.demo;

/**
 * desc...
 *
 * @author wanghaitong
 */
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doSomething() throws Exception {
        throw null;
    }
}
