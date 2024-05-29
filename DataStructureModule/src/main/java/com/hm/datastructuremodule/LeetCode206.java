package com.hm.datastructuremodule;

/**
 * 反转链表
 */
public class LeetCode206 {

    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println("反转前" + o1);
        ListNode listNode = new LeetCode206().reverseList5(o1);
        System.out.println(listNode);
    }

    /**
     * 反转方法一
     *
     * @param o1 旧链表头部
     * @return
     */
    public ListNode reverseList(ListNode o1) {
        ListNode n1 = null;
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.value, n1);
            p = p.next;
        }
        return n1;
    }

    /**
     * 方法二 依次取出旧的链表的每个节点放到新链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        List list = new List(head);
        List list2 = new List(null);
        while (true) {
            ListNode first = list.removeFirst();
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    /**
     * 方法三 递归 （推荐）
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList3(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverseList4(ListNode head) {
        if (head != null || head.next != null) {
            ListNode o1 = head.next;
            ListNode n1 = head;
            while (o1 != null) {
                //把中间节点移除，当前节点指向第三个节点
                head.next = o1.next;
                //移除的中间节点指向新的链表节点
                o1.next = n1;
                //更新 新的链表节点 指向上一步移除的中间节点
                n1 = o1;
                //向后移动一个节点
                o1 = head.next;
            }
            return n1;

        } else {
            return head;
        }
    }

    /**
     * 方法5 推荐
     * @param head
     * @return
     */
    public ListNode reverseList5(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode o1 = head;
        ListNode n1 = null;
        while (o1 != null) {
            //拿到下一个节点
            ListNode current = o1.next;
            //把当前节点的后续节点和新链接的头节点链接
            o1.next = n1;
            //新链表的头节点指向当前节点
            n1 = o1;
            //指向下一个节点
            o1 = current;
        }
        return n1;
    }

    static class List {

        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = this.head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }


    }

}

