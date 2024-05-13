package com.hm.datastructuremodule;

import java.util.Iterator;

/**
 * 双向环形链表带哨兵
 */
public class DoublyToroidalLinkedListSentinel implements Iterable<Integer> {
    private Node sentinel = new Node(null, -1, null);

    public DoublyToroidalLinkedListSentinel() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public static void main(String[] args) {
        DoublyToroidalLinkedListSentinel list = new DoublyToroidalLinkedListSentinel();
        list.addLast(5);
        list.addLast(4);
        list.addLast(3);
        list.addLast(2);
        list.addLast(1);

        list.removeByValue(1);

        for (int value : list) {
            System.out.println(value);
        }
    }

    public void addFirst(int value) {
        Node a = sentinel;
        Node b = sentinel.next;
        Node node = new Node(a, value, b);
        a.next = node;
        b.prev = node;
    }

    public void addLast(int value) {
        Node a = sentinel.prev;
        Node b = sentinel;
        Node node = new Node(a, value, b);
        a.next = node;
        b.prev = node;
    }

    public void removeFirst() {
        Node remove = sentinel.next;
        if (remove == sentinel) {
            throw new IllegalArgumentException("不合法");
        }
        Node a = sentinel;
        Node b = remove.next;
        a.next = b;
        b.prev = a;
    }

    public void removeLast() {
        Node remove = sentinel.prev;
        if (remove == sentinel) {
            throw new IllegalArgumentException("不合法");
        }
        Node a = remove.prev;
        Node b = sentinel;
        a.next = b;
        b.prev = a;
    }

    public void removeByValue(int value) {
        Node remove = findByValue(value);
        if (remove == null) {
            return;
        }
        Node a = remove.prev;
        Node b = remove.next;
        a.next = b;
        b.prev = a;
    }

    private Node findByValue(int value) {
        Node p = sentinel.next;
        while (p != sentinel) {
            if (p.value == value) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node p = sentinel.next;

            @Override
            public boolean hasNext() {
                return p != sentinel;
            }

            @Override
            public Integer next() {
                int v = p.value;
                p = p.next;
                return v;
            }
        };
    }

    private static class Node {
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
