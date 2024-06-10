package com.hm.datastructuremodule;

/**
 * 删除倒数指定节点
 */
public class LeetCode19 {

    public static void main(String[] args) {

        ListNode o6 = new ListNode(6, null);
        ListNode o5 = new ListNode(5, o6);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        ListNode listNode = new LeetCode19().removeFromEnd2(o1, 3);
        System.out.println(listNode);
    }

    public ListNode removeFromEnd(ListNode head, int n) {
        //为了解决删除第一个节点失败 没有上一个节点导致的
        ListNode s = new ListNode(-1, head);
        recursion(s, n);
        return s.next;
    }

    private int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        } else {
            //下一个节点的下标
            int nth = recursion(p.next, n);
            if (nth == n) {
                p.next = p.next.next;
            }
            return nth + 1;
        }
    }

    public ListNode removeFromEnd2(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return s.next;
    }
}
