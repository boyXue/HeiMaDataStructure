package com.hm.recursion;

/**
 * 递归反向打印字符串
 */
public class ReverPrintString {

    public static void main(String[] args) {
        f(0,"abcd");
    }

    private static void f(int n,String content){
        if (n==content.length()){
            return;
        }
        f(n+1,content);
        System.out.println(content.charAt(n));
    }
}
