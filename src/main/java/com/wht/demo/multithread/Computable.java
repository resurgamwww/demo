package com.wht.demo.multithread;

/**
 * desc.
 *
 * @author wht
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
