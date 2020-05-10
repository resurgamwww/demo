package com.wht.demo.leetCode.mock;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * @author wht
 * @date 2020/5/2 16:57
 */
public class RemoveDuplicateNodeFromLinkedList {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        int val;

        Integer lastRemovedVal = head.val - 1;


        ListNode newHead = new ListNode(lastRemovedVal);
        newHead.next = head;

        ListNode current = newHead;
        ListNode last = newHead;
        ListNode nextNode = current.next;

        while (nextNode != null) {
            //下一个节点的值
            val = nextNode.val;

            if (current.val == val ) {
                //如果当前节点的值和下一个节点的值相同，则移除当前节点和后续重复节点
                //即将上一个节点的next指向后续第一个非重复节点

                //找到后续的第一个非重复节点
                while (nextNode.next != null && nextNode.next.val == val){
                    nextNode = nextNode.next;
                }

                last.next = nextNode.next;
                current = last;
            } else {
                last = current;
                current = nextNode;
            }

            nextNode = current.next;
        }

        return newHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
    @Override
    protected void finalize(){
        System.out.println(val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.val);
        sb.append(",");
        ListNode node = this;
        while (node.next != null){
            sb.append(node.next.val);
            sb.append(",");
            node = node.next;
        }

        sb.append("]");
        return sb.toString();
    }
}
