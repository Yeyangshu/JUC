package com.yeyangshu.juc.juc002;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * <p>
 * 异常锁，程序乱入，t1异常，t2抢到锁
 */
public class SynchronizedTest09 extends Thread {

    /**
     * 计算初始值
     */
    private static int count = 0;

    /**
     * 同步方法
     */
    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1 / 0;
                System.out.println(i);
            }
        }

    }

    public static void main(String[] args) {
        SynchronizedTest09 s9 = new SynchronizedTest09();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                s9.m();
            }
        };
        new Thread(r, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r, "t2").start();
    }

    /**
     * t1 m1 start
     * t1 count = 1
     * t1 count = 2
     * t1 count = 3
     * t1 count = 4
     * t1 count = 5
     * Exception in thread "t1" java.lang.ArithmeticException: / by zero
     *     at com.yeyangshu.juc.juc002.SynchronizedTest09.m(SynchronizedTest09.java:28)
     *     at com.yeyangshu.juc.juc002.SynchronizedTest09$1.run(SynchronizedTest09.java:40)
     *     at java.lang.Thread.run(Thread.java:748)
     * t2 m1 start
     * t2 count = 6
     * t2 count = 7
     * t2 count = 8
     * t2 count = 9
     */
}