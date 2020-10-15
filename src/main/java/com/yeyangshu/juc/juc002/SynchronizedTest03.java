package com.yeyangshu.juc.juc002;

import java.util.stream.IntStream;

/**
 * synchronized关键字
 * <p>
 * 对方法加锁，等价于synchronized(this)
 */
public class SynchronizedTest03 extends Thread {

    /**
     * 启动线程数
     */
    private static final int COUNT_THREAD = 10;

    /**
     * 计算初始值
     */
    private int count = 10;

    /**
     * 重写run()方法
     */
    @Override
    public void run() {
        m();
    }

    /**
     * 方法上加synchronized，等价于synchronized (this)
     */
    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest03 s3 = new SynchronizedTest03();

        IntStream.range(0, 10).forEach(i -> new Thread(s3).start());
    }
}