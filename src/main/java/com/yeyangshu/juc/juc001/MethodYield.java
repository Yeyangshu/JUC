package com.yeyangshu.juc.juc001;

import java.util.Date;

/**
 * yield方法，当前正在执行的线程停止进入等待队列，CPU控制等待线程执行
 */
public class MethodYield {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("firstThread " + i);
                if (i % 2 == 0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("secondThread " + i);
                if (i % 2 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }
}
