package com.yeyangshu.juc.juc004;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 *
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread。
 * interrupt方法来中断等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 23:22
 */
public class CasLock01_ReentrantLock04 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        /**
         * t1线程lock.lock()，不被打断时会无限睡眠
         */
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        /**
         * t2线程lock.lockInterruptibly()，可以被t2.interrupt()打断
         */
        Thread t2 = new Thread(() -> {
            try {
                // 可以被打断
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
    /**
     * 结果：
     * t1 start
     * t2 interrupted
     * 继续等待
     */
}