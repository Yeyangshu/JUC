package com.yeyangshu.juc.juc002;

import java.util.stream.IntStream;

/**
 * synchronized关键字
 * <p>
 * synchronized(SynchronizedTest04.class)，锁的是SynchronizedTest04的对象。
 */
public class SynchronizedTest04 extends Thread {

    /**
     * 启动线程数
     */
    private static final int COUNT_THREAD = 10;

    /**
     * 计算初始值
     */
    private static int count = 10;

    /**
     * 重写run()方法
     */
    @Override
    public void run() {
        m();
        mm();
    }

    /**
     * 等价于synchronized(SynchronizedTest04.class)
     */
    public synchronized static void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    /**
     * 又拿到了SynchronizedTest04.class的锁，static方法不可以synchronized (this)
     */
    public static void mm() {
        synchronized (SynchronizedTest04.class) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }

    }

    public static void main(String[] args) {
        SynchronizedTest04 s4 = new SynchronizedTest04();

        IntStream.range(0, COUNT_THREAD).forEach(i -> new Thread(s4).start());
    }

    /**
     * Thread-1 count = 9
     * Thread-4 count = 8
     * Thread-4 count = 7
     * Thread-3 count = 6
     * Thread-3 count = 5
     * Thread-2 count = 4
     * Thread-2 count = 3
     * Thread-5 count = 2
     * Thread-5 count = 1
     * Thread-6 count = 0
     * Thread-6 count = -1
     * Thread-1 count = -2
     * Thread-10 count = -3
     * Thread-10 count = -4
     * Thread-7 count = -5
     * Thread-7 count = -6
     * Thread-8 count = -7
     * Thread-8 count = -8
     * Thread-9 count = -9
     * Thread-9 count = -10
     */

}