package com.hm.datastructuremodule;

import java.util.Arrays;

/**
 * 斐波那契数列求值 fn = f(n-1)+f(n-2)
 */
public class FIbonacci {

    public static void main(String[] args) {
        System.out.println(f(5));
    }

    public static int f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    /**
     * 优化 通过数组记录已经计算的值 避免重复计算 空间换换时间
     *
     * @param n
     * @return
     */
    public static int fibo(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        cache[0] = 0;
        cache[1] = 1;
        return f(n, cache);
    }

    private static int f(int n, int[] cache) {
        if (cache[n] != -1) {
            return cache[n];
        }
        int x = f(n - 1, cache);
        int y = f(n - 2, cache);
        cache[n] = x + y;
        return cache[n];
    }
}
