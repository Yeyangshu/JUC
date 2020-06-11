/**
 * Copyright (C), 2018-2020
 * FileName: CasLock01_ReentrantLock01
 * Author:   11077
 * Date:     2020/6/11 22:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock，可重入锁
 * tryLock尝试锁定
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 22:31
 */
public class CasLock01_ReentrantLock03 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * boolean tryLock(long time, TimeUnit unit)尝试锁定time
     * 不管是否锁定，方法都将继续执行
     */
     void m2() {
        boolean locked = false;
        try {
            locked = lock.tryLock(1, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + "：m2 locked=" + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 判断是否锁定
            if (locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        CasLock01_ReentrantLock03 r3 = new CasLock01_ReentrantLock03();
        new Thread(r3::m1, "线程一").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r3::m2, "线程二").start();
    }
}

/**
 * locked = lock.tryLock(1, TimeUnit.SECONDS);
 * 0
 * 1
 * 线程二：m2 locked=false
 * 2
 * 尝试锁定1s，
 */
