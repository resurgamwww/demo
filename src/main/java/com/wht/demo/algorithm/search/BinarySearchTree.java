package com.wht.demo.algorithm.search;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * 二叉查找树
 *
 * @author wht
 */
public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractTree<K, V> {

    protected Node<K, V> root;

    static class Node<K extends Comparable<K>, V> implements AbstractTree.Node<K, V> {
        final K key;
        volatile V value;

        volatile Node<K, V> left;
        volatile Node<K, V> right;

        volatile int count;

        public Node(K key, V value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.count = (left == null ? 0 : left.count) + (right == null ? 0 : right.count);
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

    @Override
    public int size() {
        return size(root);
    }

    protected int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        return node.count;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node<K, V> node, K key) {
        Node<K, V> p = node;
        while (p != null) {
            int i = p.key.compareTo(key);
            if (i == 0) {
                return p.value;
            } else if (i > 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if (root == null) {
            root = new Node<K, V>(key, value, 1);
            return root.value;
        }

        return put(root, key, value);
    }

    private synchronized V put(@NonNull Node<K, V> node, @Nullable K key, @Nullable V value) {
        Node<K, V> parent = null;
        Node<K, V> p = node;

        while (p != null) {
            int i = p.key.compareTo(key);
            if (i == 0) {
                p.value = value;
                return p.value;
            }

            p.count++;
            parent = p;

            if (i > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        assert parent != null;
        p = new Node<K, V>(key, value, 1);
        if (parent.key.compareTo(key) > 0) {
            parent.left = p;
        }

        return p.value;
    }

    public K max() {
        return null;
    }

    public K min() {
        Node<K, V> node = min(root);
        return node == null ? null : node.key;
    }

    private Node<K, V> min(Node<K, V> node) {
        Node<K, V> p = node;
        while (p != null) {
            if (p.left == null) {
                return p;
            }
            p = p.left;
        }
        //理论上不会走到这里
        return null;
    }

    public void removeMin() {

    }

    private Node removeMin(Node node) {
        if (node.left == null) return node.right;
        node.left = removeMin(node.left);
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    /**
     * 从node的子树中移除键等于Key的结点p。
     * 假如结点p有左右两个子结点，那么将p指向p的右子树中的最小值x,然后将x的左结点指向原p的左结点，
     * x的右结点指向原p的右结点。同时需要删除右侧的最小值，即将x的原父结点到x的引用置为空，并更新计数器
     * x的新的父结点需要一路更新计数器
     * <p>
     * 由于需要更新计数器，如果采用非递归的方法，似乎只能先查找一遍指定的Key是否存在，不然不好处理计数器
     *
     * @param node
     * @param key
     */
    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node<K, V> t = node;
            node = min(t.right);
            node.right = removeMin(node.right);
            node.left = t.left;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }
}
