package com.hm.recursion;

/**
 * 递归 阶乘
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println(f(5));
    }

    private static int f(int n) {
        if (n==1){
            return 1;
        }
        return f(n - 1) * n;
    }
}
