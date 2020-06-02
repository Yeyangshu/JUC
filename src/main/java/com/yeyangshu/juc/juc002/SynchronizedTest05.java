package com.yeyangshu.juc.juc002;

/**
 * synchronized既保证原子性，又保证可见性
 */
public class SynchronizedTest05 extends Thread {
    private /*volatile*/ int count = 100;

    @Override
    public synchronized void run() {
        m();
    }

    public void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest05 s5 = new SynchronizedTest05();

        for (int i = 0; i < 100; i++) {
            new Thread(s5).start();
        }
    }
}
