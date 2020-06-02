package com.yeyangshu.juc.juc002;

/**
 * 对某个对象加锁
 */
public class SynchronizedTest01 extends Thread {
    private Object o = new Object();
    private int count = 10;

    @Override
    public void run() {
        m();
    }

    public void m() {
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest01 s1 = new SynchronizedTest01();

        for (int i = 0; i < 10; i++) {
            new Thread(s1).start();
        }
    }
}
