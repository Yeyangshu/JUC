package com.yeyangshu.juc.juc004;


import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 *
 * 一个资源能够被多个读线程访问，或者被一个写线程访问，但是不能同时存在读写线程
 * 读写锁，其实就是共享锁（读锁）和排它锁（写锁、互斥锁）
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 10:28
 */
public class CasLock05_ReadWriteLock {

    /**
     * 模拟写入操作值
     */
    private static int value;

    /**
     * ReentrantLock
     */
    static Lock lock = new ReentrantLock();

    /**
     * readWriteLock
     */
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 读锁对象
     */
    static Lock readlock = readWriteLock.readLock();

    /**
     * 写锁对象
     */
    static Lock writeLock = readWriteLock.writeLock();

    /**
     * 模拟读取操作
     *
     * @param lock
     */
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


    /**
     * 模拟写入操作
     *
     * @param lock
     * @param v
     */
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

        // 需要20s才能读取、写入完毕
        //Runnable readR = () -> read(lock);
        //Runnable writeR = () -> write(lock, new Random().nextInt());

        Runnable readR = () -> read(readlock);
        Runnable writeR = () -> write(writeLock, new Random().nextInt());

        // 创建18个读取线程
        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }

        // 创建2个写入线程
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }

    /**
     * 结果：
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
     *
     * 使用ReentrantLock需要20s才能读取写入完毕，效率慢
     * 使用ReentrantReadWriteLock效率非常快
     */
}