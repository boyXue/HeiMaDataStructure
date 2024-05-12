package com.hm.datastructuremodule;


import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 带哨兵的单链表
 */
public class SinglyLinkedListSentinel implements Iterable<Integer> {

    private Node head = new Node(666, null);//头指针

    public static void main(String[] args) {
        SinglyLinkedListSentinel singlyLinkedList = new SinglyLinkedListSentinel();
//        singlyLinkedList.addFirst(5);
//        singlyLinkedList.addFirst(4);
//        singlyLinkedList.addFirst(3);
//        singlyLinkedList.addFirst(2);
//        singlyLinkedList.addFirst(1);
        singlyLinkedList.addLast(1);
        singlyLinkedList.addLast(2);
        singlyLinkedList.addLast(3);
        singlyLinkedList.addLast(4);

//        singlyLinkedList.loop2(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });

        singlyLinkedList.removeFirst();
        for (int node : singlyLinkedList) {
            System.out.println(node);
        }
//        System.out.println(singlyLinkedList.get(3));
    }


    /**
     * 添加链表首位
     *
     * @param value 节点值
     */
    public void addFirst(int value) {
        insert(0, value);
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        return node.value;
    }

    public void insert(int index, int value) {
        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node node = new Node(value, prev.next);
        prev.next = node;
    }

    public void removeFirst() {
        remove(0);
    }

    public void remove(int index) {

        Node prev = findNode(index - 1);
        if (prev == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        Node node = prev.next;
        if (node == null) {
            throw new IllegalArgumentException("没有该节点");
        }
        prev.next = node.next;
    }

    private Node findNode(int index) {
        int i = -1;
        for (Node p = head; p != null; p = p.next, i++) {
            if (index == i) {
                return p;
            }
        }
        return null;
    }


    /**
     * 尾部添加
     *
     * @param value
     */
    public void addLast(int value) {
        Node last = findLast();
        last.next = new Node(value, null);
    }

    private Node findLast() {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }


    /**
     * 遍历方法1
     *
     * @param consumer
     */
    public void loop(Consumer<Integer> consumer) {
        Node node = head.next;
        while (node != null) {
            consumer.accept(node.value);
            node = node.next;
        }

    }

    /**
     * 遍历方法2
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node node = head.next; node != null; node = node.next) {
            consumer.accept(node.value);
        }
    }

    /**
     * 遍历方法3
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {
            Node node = head.next;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Integer next() {
                int current = node.value;
                node = node.next;
                return current;
            }
        };
    }


    /**
     * 节点类
     */
    private static class Node {

        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}

