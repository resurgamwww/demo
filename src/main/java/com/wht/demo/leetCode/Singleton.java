package com.wht.demo.leetCode;

/**
 * desc.
 *
 * @author wht
 */
public enum  Singleton {
    ;

    Singleton() {
    }

    public void doSomething(){
    }

    public Singleton getInstance(){
        return this;
    }
}
