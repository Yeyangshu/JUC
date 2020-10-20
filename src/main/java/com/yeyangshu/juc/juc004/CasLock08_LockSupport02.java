package com.yeyangshu.juc.juc004;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 *
 * 1.LockSupport不需要synchronized加锁就可以实现线程的阻塞和唤醒
 * 2.LockSupport.unpark()可以先于LockSupport.park()执行，并且线程不会阻塞
 * 3.如果一个线程处于等待状态，连续调用了两次LockSupport.park()，会使该线程永远无法被唤醒
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 11:16
 */
public class CasLock08_LockSupport02 {

    public static void main(String[] args) {

        /**
         * 使用LockSupport.park()阻塞当前线程t，LockSupport.unpark(t)唤醒线程t
         */
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);

                // LockSupport.park()阻塞当前线程t
                if (i == 5) {
                    LockSupport.park();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程t
        t.start();

        // 唤醒线程t
        LockSupport.unpark(t);
    }

    /**
     * 结果：
     * 0
     * 1
     * 2
     * 3
     * 4
     * 5
     * 6
     * 7
     * 8
     * 9
     *
     * 线程结束
     */
}