package com.yeyangshu.juc.juc002;

/**
 * 对方法加锁，等价于synchronized(this)
 */
public class SynchronizedTest03 extends Thread {
    private int count = 10;

    @Override
    public void run() {
        m();
    }

    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest03 s3 = new SynchronizedTest03();

        for (int i = 0; i < 10; i++) {
            new Thread(s3).start();
        }
    }
}
