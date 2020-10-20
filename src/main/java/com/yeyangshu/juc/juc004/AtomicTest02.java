package com.yeyangshu.juc.juc004;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * CAS无锁优化
 *
 * synchronized、AtomicLong、LongAdder的使用和对比
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/10 23:50
 */
public class AtomicTest02 {

    /**
     * synchronized test
     */
    static long count1 = 0L;

    /**
     * AtomicLong test
     */
    static AtomicLong count2 = new AtomicLong(0L);

    /**
     * LongAdder test
     */
    static LongAdder count3 = new LongAdder();

    /**
     * 启动线程数
     */
    static final int THREAD_COUNTS = 1000;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[THREAD_COUNTS];

        System.out.println("-------------synchronized------------------");
        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (lock) {
                        count1++;
                    }
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("synchronized：" + count1 + " time " + (end - start));

        System.out.println("-------------AtomicLong------------------");

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        end = System.currentTimeMillis();
        System.out.println("AtomicLong：" + count2 + " time " + (end - start));


        System.out.println("-------------LongAdder------------------");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder：" + count3 + " time " + (end - start));
    }
}