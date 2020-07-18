package com.wht.demo.algorithm.search;

/**
 * desc.
 *
 * @author wht
 */
public abstract class AbstractTree<K extends Comparable<K>, V> {

    protected Node<K, V> root;

    public abstract int size();

    public abstract V get(K key);

    public abstract V put(K key, V value);

    public abstract K max();

    public abstract K min();

    public abstract void removeMin();

    public abstract void remove(K key);

    interface Node<K extends Comparable<K>, V>{

    }

}
