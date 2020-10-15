package com.yeyangshu.juc.juc002;

import java.util.stream.IntStream;

/**
 * synchronized关键字
 * <p>
 * synchronized既保证原子性，又保证可见性
 */
public class SynchronizedTest05 extends Thread {

    /**
     * 计算初始值，volatile可以保证可见性
     */
    private /*volatile*/ int count = 100;

    /**
     * 重写run()方法
     */
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

        IntStream.range(0, 100).forEach(i -> new Thread(s5).start());
    }

    /**
     * 情况一：不加锁的话，可能出现数据混乱，线程一读到count=3，count--还未输出，线程二读到count=3
     * private int count = 100;
     * public void m() {
     *     try {
     *         Thread.sleep(1);
     *     } catch (InterruptedException e) {
     *         e.printStackTrace();
     *     }
     *     count--;
     *     System.out.println(Thread.currentThread().getName() + " count = " + count);
     *  }
     * Thread-88 count = 10
     * Thread-73 count = 3
     * Thread-55 count = 2
     * Thread-31 count = 1
     * Thread-42 count = 3
     * Thread-69 count = 3
     * Thread-60 count = 3
     *
     * 情况二：不加锁的话，为了保证数据正常，count添加volatile关键字
     * private volatile int count = 100;
     * public void m() {
     *     count--;
     *     System.out.println(Thread.currentThread().getName() + " count = " + count);
     * }
     *
     * 情况三：加锁，数据正常
     * private int count = 100;
     * public void m() {
     *     count--;
     *     System.out.println(Thread.currentThread().getName() + " count = " + count);
     * }
     */
}