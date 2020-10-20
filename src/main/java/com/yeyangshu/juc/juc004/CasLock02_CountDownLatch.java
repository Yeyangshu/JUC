package com.yeyangshu.juc.juc004;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 *
 * 使一个线程等待其它线程执行完毕后再执行
 * 通过计数器实现，计数器初始值是线程的数量。每当一个线程执行完毕计数器值-1，直到为0开始工作
 *
 * 方法：
 * // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 * public void await() throws InterruptedException { };
 * // 和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 * public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
 * // 将count值减1
 * public void countDown() { };
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 22:50
 */
public class CasLock02_CountDownLatch {

    /**
     * 线程数
     */
    static final int THREAD_COUNT = 100;

    public static void main(String[] args) {
        //前两个例子不好理解
        //usingJoin();
        //usingCountDownLatch();
        usingCountDownLatch2();
    }


    private static void usingCountDownLatch2() {

        /**
         * 新建count=2的latch
         */
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
                System.out.println("子线程" + Thread.currentThread().getName() + "，count = " + latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
                System.out.println("子线程" + Thread.currentThread().getName() + "，count = " + latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println("主线程调用latch.await()，等待2个子线程执行完毕，count = 0时继续执行");
            latch.await();
            System.out.println("2个子线程已经执行完毕，count = " + latch.getCount());
            System.out.println("主线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 结果：
     * 主线程调用latch.await()，等待2个子线程执行完毕，count = 0时继续执行
     * 子线程Thread-0正在执行
     * 子线程Thread-1正在执行
     * 子线程Thread-0执行完毕
     * 子线程Thread-0，count = 1
     * 子线程Thread-1执行完毕
     * 子线程Thread-1，count = 0
     * 2个子线程已经执行完毕，count = 0
     * 主线程继续执行
     */

    /**
     * usingCountDownLatch
     */
    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[THREAD_COUNT];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                    latch.countDown();
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("latch end");
    }

    /**
     * usingJoin
     */
    private static void usingJoin() {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 1000; j++) {
                    result += j;
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("join end");
    }
}