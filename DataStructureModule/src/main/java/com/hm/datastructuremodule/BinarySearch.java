package com.hm.datastructuremodule;

//二分查找
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 8, 8, 10, 89, 100, 145, 184};
//        int i = binarySearch(arr, 0, arr.length - 1, 145);
//        int i = binarySearch4(arr, 2);
        int i = search(arr, 10);
        System.out.print("查找值的下标：" + i);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == findVal) {
                return mid;
            } else if (findVal > arr[mid]) {
                return binarySearch(arr, mid + 1, right, findVal);
            } else if (findVal < arr[mid]) {
                return binarySearch(arr, left, mid - 1, findVal);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * @param arr    待查询数组
     * @param target 目标值
     * @return 索引或者-1
     */
    public static int binarySearch2(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        int mid = 0;
        while (i <= j) {
            //这里使用右移运算符，是因为如果i，j的值特别大，i+j超出了Int的最大值。在执行（i+j）/2时，因为Java中最高位是符号位，导致得出的数字是负数。
            //右移一位相当于 数值除以2向下取整
            mid = (i + j) >>> 1;
            if (arr[mid] == target) {
                return mid;
            } else {
                if (arr[mid] > target) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
        }
        return -1;
    }


    /**
     * 查询多个重复数字最左值下标
     *
     * @param arr    待查找升序数组
     * @param target 目标值
     * @return 最左值下标
     */
    public static int binarySearch3(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        int result = -1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target < arr[m]) {
                j = m - 1;
            } else if (target > arr[m]) {
                i = m + 1;
            } else {
                result = m;
                j = m - 1;
            }
        }
        return result;
    }

    /**
     * @param arr
     * @param target
     * @return 返回>=target的最靠左的位置
     */
    public static int binarySearch4(int[] arr, int target) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int m = (i + j) >>> 1;
            if (target <= arr[m]) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        return i;
    }

    public static int search(int[] a,int target){
        return f(a,target,0,a.length-1);
    }

    private static int f(int[] a, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int m = (left + right) >>> 1;
        if (target < a[m]) {
            return f(a, target, left, m - 1);
        } else if (target > a[m]) {
            return f(a, target, m + 1, right);
        } else {
            return m;
        }

    }
}
