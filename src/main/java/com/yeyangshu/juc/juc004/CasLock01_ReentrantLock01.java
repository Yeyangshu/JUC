package com.yeyangshu.juc.juc004;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock
 *
 * 复习synchronized，可重入锁
 * synchronized方法可以调用synchronized方法
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 22:09
 */
public class CasLock01_ReentrantLock01 {

    /**
     * 同步方法m1
     */
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) {
                m2();
            }
        }
    }

    /**
     * 同步方法m2
     */
    synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "：m2");
    }

    public static void main(String[] args) {
        CasLock01_ReentrantLock01 r1 = new CasLock01_ReentrantLock01();
        new Thread(r1::m1, "线程一").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2, "线程二").start();
    }

    /**
     * 0
     * 1
     * 2
     * 线程一：m2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     * 线程二：m2
     */
}