package com.hm.datastructuremodule;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * 动态数组
 * 数组插入或删除的性能:
 * 头部、中部 时间复杂度：O(n)
 * 尾部 时间复杂度：O(1)
 */
public class DynamicArray implements Iterable {
    private int size = 0;//数组大小
    private int capacity = 8;//容量
    private int[] array = new int[capacity];


    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);

        dynamicArray.add(5, 2);
//        dynamicArray.foreach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });

// 迭代器遍历使用增强型for循环打印
//        for (Object element: dynamicArray){
//            System.out.println(element);
//        }

        //数据流遍历
        dynamicArray.stream().forEach(value -> {
            System.out.println(value);
        });
    }

    public void addLast(int element) {
//        array[size] = element;
//        size++;
        add(element, size);
    }

    public void add(int element, int index) {
        checkAndGrow();
        if (index < 0 || index > size) {
            return;
        }
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = element;
        size++;
    }

    /**
     * 容量检查
     */
    private void checkAndGrow() {
        //添加数据的时候进行数组的初始化，避免刚开始不添加数据时，占用内存
        if (size == 0) {
            array = new int[capacity];
        } else if (size == capacity) {
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public int get(int index) {
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            return -1;
        }
    }

//    public void forEach(){
//        for (int i = 0; i < size; i++) {
//            System.out.println(array[i]);
//        }
//    }

    /**
     * 由调用者觉得具体的操作逻辑
     *
     * @param consumer 返回每个数组的值供调用者使用
     */
    public void foreach(Consumer<Integer> consumer) {
        for (int i = 0; i < array.length; i++) {
            consumer.accept(array[i]);
        }
    }

    /**
     * 迭代器遍历
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Integer next() {
                return array[i++];
            }
        };
    }

    /**
     * 数据流遍历
     * @return
     */
    public IntStream stream() {
//        直接获取array会把无效的值传递进去
//        return IntStream.of(array);
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

    public int remove(int index) {
        //必须是小于数组长度的下标
        if (index < size) {
            int removed = array[index];
            //如果下标是数组的最后一位 size：5 index：4 没必要进行下面的arraycopy逻辑
            if (index < size - 1) {
                System.arraycopy(array, index + 1, array, index, size - index - 1);
            }
            size--;
            return removed;
        } else {
            return -1;
        }
    }
}
