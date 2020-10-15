package com.yeyangshu.juc.juc002;

import java.util.stream.IntStream;

/**
 * synchronized关键字
 * <p>
 * 对某个对象加锁
 * 缺点：每次都要新建一个对象，麻烦
 */
public class SynchronizedTest01 extends Thread {

    /**
     * 启动线程数
     */
    private static final int COUNT_THREAD = 10;

    /**
     * 锁定的对象
     */
    private Object o = new Object();

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
     * synchronized (o)
     */
    public void m() {
        // 任何线程想要执行下面的代码，必须先拿到o的锁
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest01 s1 = new SynchronizedTest01();

        // 启动十个线程
        IntStream.range(0, COUNT_THREAD).forEach(i -> new Thread(s1).start());
    }
}