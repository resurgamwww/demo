package com.wht.demo.algorithm.search;

import java.util.Objects;

/**
 * 平衡查找树
 * <p>
 * 为了保证在最坏情况下所有查找都能在~lgN次比较内结束，需要让树保持平衡，希望树高为~lgN，但是动态插入中保持树的完美平衡的代价太高了。这里稍微放松一下要求，对范围查找不要求在对数时间内完成
 *
 * @author wht
 */
public class BalanceSearchTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    static class Node<K extends Comparable<K>, V> {
        final K key;
        volatile V value;

        volatile Node<K, V> left;
        volatile Node<K, V> right;

        volatile int count;
        /**
         * 指向该链接的颜色，即从父结点指向该结点的颜色
         */
        volatile boolean color;

        public Node(K key, V value, int count, boolean color) {
            this.key = key;
            this.value = value;
            this.count = count;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    /**
     * 红黑树的插入需要按顺序进行以下操作：
     * 如果右子结点是红色的而左子结点是黑色的，进行左旋
     * 如果左子结点是红色的且它的左子结点也是红色的，进行右旋
     * 如果左右子节点均为红色，进行颜色转换（即将两个子结点颜色转为黑色，父结点由黑转为红色）。
     */
    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value, 1, RED);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;

        if (isRed(node.right) && isBlack(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isBlack(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private boolean isRed(Node<K, V> node) {
        if (node == null) return false;

        return node.color == RED;
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }


    /**
     * 对当前结点进行左旋，当前结点的右子结点必须为红色，左子结点必须为黑色
     *
     * @param node 当前结点
     * @return 左旋后的当前结点
     */
    private Node<K, V> rotateLeft(Node<K, V> node) {

        Node<K, V> right = node.right;

        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        right.count = node.count;
        node.count = 1 + size(node.left) + size(node.right);

        return right;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {

        Node<K, V> left = node.left;

        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        left.count = node.count;
        node.count = 1 + size(node.left) + size(node.right);

        return left;
    }

    private void flipColors(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public int size() {
        return size(root);
    }

    protected int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        return node.count;
    }

    public void remove(K key) {
        if (root == null) return;

        root = remove(root, key);
    }

    /**
     *
     */
    protected Node<K, V> remove(Node<K, V> node, K key) {

        return null;

    }

}
