package com.hm.datastructuremodule;

import java.util.Iterator;

/**
 * 双向链表带哨兵
 */
public class DoublyKinkedListSentinel implements Iterable<Integer> {
    private Node head;//头哨兵
    private Node tail;//尾哨兵

    public static void main(String[] args) {
        DoublyKinkedListSentinel doublyKinkedListSentinel = new DoublyKinkedListSentinel();
        doublyKinkedListSentinel.insert(0, 1);
        doublyKinkedListSentinel.insert(1, 2);
        doublyKinkedListSentinel.insert(2, 3);

        doublyKinkedListSentinel.remove(2);
        for (int node : doublyKinkedListSentinel) {
            System.out.println(node);
        }
    }

    public DoublyKinkedListSentinel() {
        head = new Node(null, 666, null);
        tail = new Node(null, 888, null);
        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(int value) {
        insert(0, value);
    }

    public void removeFirst(){
        remove(0);
    }

    public void addLast(int value) {
        Node last = tail.prev;
        Node added = new Node(last, value, tail);
        last.next = added;
        tail.prev = added;
    }

    public void removeLast(){
        Node last = tail.prev;
        if (last == head){
            throw new IllegalArgumentException(String.format("index [%d] 不合法", 0));
        }
        Node prev = last.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node next = prev.next;
        Node node = new Node(prev, value, next);
        prev.next = node;
        next.prev = node;
    }

    public void remove(int index) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node remove = prev.next;
        if (remove == tail) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node next = remove.next;
        prev.next = next;
        next.prev = prev;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != tail; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node node = head.next;

            @Override
            public boolean hasNext() {
                return node != tail;
            }

            @Override
            public Integer next() {
                int current = node.value;
                node = node.next;
                return current;
            }
        };
    }

    public static class Node {
        Node prev;//上一个节点
        int value;
        Node next;//下一个节点

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }
}
