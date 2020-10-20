package com.yeyangshu.juc.juc004;

import java.util.concurrent.Semaphore;

/**
 * 信号灯
 *
 * 作用是控制线程的并发数量
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 10:52
 */
public class CasLock06_Semaphore {
    public static void main(String[] args) {
        /**
         * 初始化了2个通路，并且是公平的
         */
        Semaphore semaphore = new Semaphore(2, true);

        /**
         * T1线程占用后释放
         */
        new Thread(() -> {
            try {
                // acquire()，线程进入将会占用的通路为1
                // acquire(int permits)，线程进入将会占用的通路为permits
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // release()，归还通路为1
                // release(int permits)，归还通路为permits
                semaphore.release();
            }
        }).start();

        /**
         * T2线程占用后不释放
         */
        new Thread(() -> {
            try {
                // acquire()，线程进入将会占用的通路为1
                // acquire(int permits)，线程进入将会占用的通路为permits
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        /**
         * T2线程占用后不释放
         */
        new Thread(() -> {
            try {
                // acquire()，线程进入将会占用的通路为1
                // acquire(int permits)，线程进入将会占用的通路为permits
                semaphore.acquire();
                System.out.println("T3 running...");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Semaphore semaphore = new Semaphore(2, true);
     * T1 running...
     * T2 running...
     * T3 running...
     * T1用完释放掉，T2使用，T2没有释放，还剩一个通路，T3执行
     *
     * Semaphore semaphore = new Semaphore(1, true);
     * T1 running...
     * T2 running...
     * T1用完释放掉，T2使用，T2没有释放，T3无法执行，程序阻塞
     */
}