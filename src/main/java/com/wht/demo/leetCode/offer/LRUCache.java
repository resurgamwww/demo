package com.wht.demo.leetCode.offer;

import java.util.HashMap;

/**
 * desc.
 *
 * @author wht
 */
public class LRUCache {
    static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

    }

    int capacity;

    HashMap<Integer, Node> map;

    //这里为了后续处理方便，头尾结点设置为独立的空结点
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }

        //如果key存在，将其挪到第一位
        moveToFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        //先检查map中是不是已经有了
        Node node = map.get(key);
        if (node == null) {
            //如果之前没有，那么新建结点放到头部即可，如果超出容量，需要移除尾部结点。
            if (map.size() == capacity) {
                Node pre = tail.pre;
                tail.pre = pre.pre;
                pre.pre.next = tail;
                map.remove(pre.key);
            }

            node = new Node(key, value);
            map.put(key,node);
            insertFirst(node);
        } else {
            //如果已经存在，将该结点移到头部
            node.val = value;
            moveToFirst(node);
        }
    }

    public void moveToFirst(Node node) {
        if (head.next == node) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;


        insertFirst(node);
    }

    public void insertFirst(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next = node;
        node.next.pre = node;
    }
}
