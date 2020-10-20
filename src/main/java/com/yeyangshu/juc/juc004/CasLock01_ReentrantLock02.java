package com.yeyangshu.juc.juc004;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 *
 * ReentrantLock，可重入锁
 * ReentrantLock替代synchronized
 * lock.lock()加锁，必须要finally里lock.unlock()解锁！
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 22:31
 */
public class CasLock01_ReentrantLock02 {

    Lock lock = new ReentrantLock();

    void m1() { // synchronized(this)
        try {
            // 加锁
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 必须要手动解锁！
            lock.unlock();
        }
    }

    void m2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "：m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CasLock01_ReentrantLock02 r2 = new CasLock01_ReentrantLock02();
        new Thread(r2::m1, "线程一").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r2::m2, "线程二").start();
    }

    /**
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
     * 线程二：m2
     */
}