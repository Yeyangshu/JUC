package com.yeyangshu.juc.juc002;

/**
 * synchronized关键字
 * <p>
 * 同步方法和非同步方法可以同时调用
 */
public class SynchronizedTest06 extends Thread {

    /**
     * 同步方法
     */
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    /**
     * 非同步方法
     */
    private void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 start");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }


    public static void main(String[] args) {
        SynchronizedTest06 s6 = new SynchronizedTest06();

        new Thread(s6::m1, "t1").start();
        new Thread(s6::m2, "t2").start();
    }

    /**
     * 同步方法和非同步方法可以同时调用。线程访问m1需要加锁，访问m2不需要加锁，可以访问
     * t1 m1 start
     * t2 m2 start
     * t2 m2 end
     * t1 m1 end
     */
}