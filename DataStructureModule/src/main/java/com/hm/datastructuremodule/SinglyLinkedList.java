package com.hm.datastructuremodule;


import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList implements Iterable<Integer> {

    private Node head;//头指针

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
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

//        singlyLinkedList.remove(3);

//        singlyLinkedList.insert(0,10);
//        for (int node : singlyLinkedList) {
//            System.out.println(node);
//        }
        singlyLinkedList.loop3(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("before:" + integer);
            }
        }, new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("after:" + integer);
            }
        });
    }


    /**
     * 添加链表首位
     *
     * @param value 节点值
     */
    public void addFirst(int value) {
        head = new Node(value, head);
    }

    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
        }
        return node.value;
    }

    public void insert(int index, int value) {
        if (index > 0) {
            Node prev = findNode(index - 1);
            if (prev == null) {
                throw new IllegalArgumentException(String.format("index [%d] 不合法", index));
            }
            Node node = new Node(value, prev.next);
            prev.next = node;
        } else {
            addFirst(value);
        }
    }

    public void removeFirst() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    public void remove(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
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
        int i = 0;
        for (Node p = head; p != null; p = p.next, i++) {
            if (index == i) {
                return p;
            }
        }
        return null;
    }

    /**
     * 遍历方法1
     *
     * @param consumer
     */
    public void loop(Consumer<Integer> consumer) {
        if (head != null) {
            Node node = head;
            while (node != null) {
                consumer.accept(node.value);
                node = node.next;
            }
        }
    }

    /**
     * 遍历方法2
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node node = head; node != null; node = node.next) {
            consumer.accept(node.value);
        }
    }

    public void loop3(Consumer<Integer> before, Consumer<Integer> after) {
        recursion(head, before, after);
    }

    private void recursion(Node curr, Consumer<Integer> before, Consumer<Integer> after) {//针对某一个节点进行的操作

        if (curr != null) {
            before.accept(curr.value);
            recursion(curr.next, before, after);

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
            Node node = head;

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
     * 尾部添加
     *
     * @param value
     */
    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    private Node findLast() {
        if (head == null) {
            return null;
        }
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
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

