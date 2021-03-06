package com.yeyangshu.juc.juc001;

import java.util.Date;

/**
 * yield 方法，当前正在执行的线程停止进入等待队列，CPU 控制等待线程执行
 * 暂停当前正在执行的线程对象，并执行其他线程。
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

    /**
     * firstThread 0
     * secondThread 0
     * firstThread 1
     * firstThread 2
     * secondThread 1
     * secondThread 2
     * firstThread 3
     * firstThread 4
     * secondThread 3
     * secondThread 4
     * firstThread 5
     * firstThread 6
     * secondThread 5
     * secondThread 6
     * firstThread 7
     * firstThread 8
     * secondThread 7
     * secondThread 8
     * firstThread 9
     * secondThread 9
     */
}
