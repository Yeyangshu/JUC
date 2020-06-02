package com.yeyangshu.juc.juc002;

/**
 * 对当前对象加锁
 */
public class SynchronizedTest02 extends Thread {
    private int count = 10;

    @Override
    public void run() {
        m();
    }

    public void m() {
        synchronized(this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest02 s2 = new SynchronizedTest02();

        for (int i = 0; i < 10; i++) {
            new Thread(s2).start();
        }
    }
}
