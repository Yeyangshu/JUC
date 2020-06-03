package com.yeyangshu.juc.juc002;

/**
 * synchronized是可重入锁
 *
 */
public class SynchronizedTest08 {

    public synchronized void m1() {
        System.out.println("m1 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    private synchronized void m2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }


    public static void main(String[] args) {
        new SynchronizedTest08().m1();
        new SynchronizedTest08Child().m1();
    }
}

/**
 * 模拟父子类
 */
class SynchronizedTest08Child extends SynchronizedTest08 {
    @Override
    public synchronized void m1() {
        System.out.println("child start");
        super.m1();
        System.out.println("child end");
    }
}
