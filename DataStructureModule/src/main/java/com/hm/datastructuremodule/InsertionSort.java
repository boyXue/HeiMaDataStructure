package com.hm.datastructuremodule;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] array = {6, 5, 8, 3, 9, 7};
        sort2(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] a) {
        insertion(a, 1);
    }

    public static void sort2(int[] a) {
        insertion2(a, 1, 1);
    }

    /**
     *
     * @param a 待排序数组
     * @param low 未排序数的左边界
     */
    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }
        int t = a[low];
        int i = low - 1;//已排序区域指针
        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];//比t大的值向右移动一位为了空出要插入的位置
            i--;
        }
        if (i+1 != low) {
            a[i + 1] = t;
        }
        insertion(a, low + 1);
    }

    //扩展练习 指定某个区间的排序
    private static void insertion2(int[] a, int low, int height) {
        if (low > height) {
            return;
        }
        int t = a[low];
        int i = low - 1;//已排序区域指针
        while (i >= 0 && a[i] > t) {
            a[i + 1] = a[i];//比t大的值向右移动一位为了空出要插入的位置
            i--;
        }
        a[i + 1] = t;
        insertion2(a, low + 1, height);
    }
}
