package com.hm.datastructuremodule;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {6, 5, 8, 3, 9, 7};
        bubble(array, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 一次循环会把当前数组最大值放在最后的位置
     *
     * @param a 待排序数组
     * @param j 未排序区域右边界
     */
    private static void bubble(int[] a, int j) {
        if (j == 0) {
            return;
        }
        //x变量的意义：如果发生交换 x为i表示x的右边都是排好序的，x的左边不是。进行递归的时候可以避免每次j-1可能存在的重复循环
        int x = 0;
        int swap = -1;
        for (int i = 0; i < j; i++) {
            if (a[i] > a[i + 1]) {
                swap = a[i];
                a[i] = a[i + 1];
                a[i + 1] = swap;
                x = i;
            }
        }
        bubble(a, x);
//        bubble(a, j-1);
    }
}
