package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue：阻塞队列(基于链表)
 * 基于链表实现的阻塞队列，想比于不阻塞的ConcurrentLinkedQueue，它多了一个容量限制，如果不设置默认为int最大值。
 * 阻塞方法，put()、take()，put添加，如果满了线程阻塞，take取值，如果空了线程阻塞。
 * 实现了生产者和消费者
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/27 10:10
 */
public class Concurrent03_BlockingQueue02_LinkedBlockingQueue {
    static BlockingQueue<String> strings = new LinkedBlockingQueue<>();
    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    // 添加元素，满了就会阻塞
                    strings.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p1").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (;;) {
                    try {
                        // take取，如果空了，就会阻塞
                        System.out.println(Thread.currentThread().getName() + " take - " + strings.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }
    }

    /**
     *    take()阻塞：notEmpty.await();
     *    public E take() throws InterruptedException {
     *         final E x;
     *         final int c;
     *         final AtomicInteger count = this.count;
     *         final ReentrantLock takeLock = this.takeLock;
     *         takeLock.lockInterruptibly();
     *         try {
     *             while (count.get() == 0) {
     *                 notEmpty.await();
     *             }
     *             x = dequeue();
     *             c = count.getAndDecrement();
     *             if (c > 1)
     *                 notEmpty.signal();
     *         } finally {
     *             takeLock.unlock();
     *         }
     *         if (c == capacity)
     *             signalNotFull();
     *         return x;
     *     }
     *
     */
}