package com.yeyangshu.juc.juc002;

/**
 * synchronized(SynchronizedTest04.class)，锁的是SynchronizedTest04的对象。
 */
public class SynchronizedTest04 extends Thread {
    private static int count = 10;

    @Override
    public void run() {
        m();
//        mm();
    }

    public synchronized static void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

//    public static void mm() {
//        synchronized (SynchronizedTest04.class) {
//            count--;
//            System.out.println(Thread.currentThread().getName() + " count = " + count);
//        }
//
//    }

    public static void main(String[] args) {
        SynchronizedTest04 s4 = new SynchronizedTest04();

        for (int i = 0; i < 10; i++) {
            new Thread(s4).start();
        }
    }
}
