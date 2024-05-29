package com.hm.datastructuremodule;

/**
 * 删除指定链表节点
 */
public class LeetCode203 {

    public static void main(String[] args) {
        ListNode o5 = new ListNode(7, null);
        ListNode o4 = new ListNode(7, o5);
        ListNode o3 = new ListNode(7, o4);
        ListNode o2 = new ListNode(7, o3);
        ListNode o1 = new ListNode(7, o2);
        System.out.println(o1);
        System.out.println(new LeetCode203().removeElements2(o1, 7));
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s.next;
        while (p2 != null) {
            if (p2.value == val) {
                p1.next = p2.next;
            } else {
                p1 = p1.next;
            }
            p2 = p2.next;
        }
        return s.next;
    }

    /**
     * 递归方式
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.value == val) {
            return removeElements2(head.next, val);
        } else {
            head.next = removeElements2(head.next, val);
            return head;
        }
    }
}
