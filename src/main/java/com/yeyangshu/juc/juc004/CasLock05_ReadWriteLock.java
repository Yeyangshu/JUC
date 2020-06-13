/**
 * Copyright (C), 2018-2020
 * FileName: CasLock04_Phaser
 * Author:   11077
 * Date:     2020/6/13 9:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock，读写锁，其实就是共享锁（读锁）和排它锁（写锁）
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 10:28
 */
public class CasLock05_ReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁对象
    static Lock readlock = readWriteLock.readLock();
    // 写锁对象
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readR = () -> read(readlock);
        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }

    /**
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * read over
     * write over
     * write over
     */
}