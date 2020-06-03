package com.yeyangshu.juc.juc002;

import java.util.concurrent.TimeUnit;

/**
 * 异常锁,t1异常，t2抢到锁
 */
public class SynchronizedTest09 extends Thread {
    private static int count = 0;

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
}
