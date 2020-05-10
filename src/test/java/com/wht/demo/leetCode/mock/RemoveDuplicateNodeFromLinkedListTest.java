package com.wht.demo.leetCode.mock;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author wht
 * @date 2020/5/2 17:39
 */
public class RemoveDuplicateNodeFromLinkedListTest {

    @Test
    public void deleteDuplicates() {
        ListNode head;
        head = new ListNode(1);


        int i = 1;
        ListNode node;
        node = head;
        boolean flag = true;
        while ( i < 10){

            node.next = new ListNode(i);
            node = node.next;

            if (flag){
                i ++;
            }
            flag = !flag;
        }
        System.out.println(head);
        ListNode node1 = RemoveDuplicateNodeFromLinkedList.deleteDuplicates(head);
        System.out.println(node1);
    }

    @Test
    public void testGc(){
        ListNode head;
        head = new ListNode(0);


        int i = 1;
        ListNode node;
        node = head;
        while ( i < 5){
            node.next = new ListNode(i);
            node = node.next;
            i ++;
        }


        //ListNode newNode = head.next.next;

        head = null;
        System.gc();

        System.out.println();
    }
}