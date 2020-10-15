package com.yeyangshu.juc.juc002;

import java.util.stream.IntStream;

/**
 * synchronized关键字
 * <p>
 * 对当前对象加锁
 */
public class SynchronizedTest02 extends Thread {

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
     * synchronized (this)
     */
    public void m() {
        // 任何线程想要执行下面的代码，必须先拿到this的锁
        synchronized (this) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest02 s2 = new SynchronizedTest02();

        IntStream.range(0, COUNT_THREAD).forEach(i -> new Thread(s2).start());
    }
}