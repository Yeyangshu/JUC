package com.yeyangshu.juc.juc005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T01_04_WaitAndNotify {

    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T01_04_WaitAndNotify t = new T01_04_WaitAndNotify();

        /**
         * 锁
         */
        final Object lock = new Object();

        /**
         * 线程二
         * 监控集合大小
         */
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 启动");
                if (t.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                // 通知t1继续执行
                lock.notify();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 线程一
         * 集合添加元素
         */
        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    t.add(new Object());
                    System.out.println("add" + i);

                    if (t.size() == 5) {
                        lock.notify();

                        // t1释放锁，使t2可以得到锁
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }

    /**
     * 设计思路：
     * 利用线程的wait和notify
     *
     * 结果：
     * t2 启动
     * t1 启动
     * add0
     * add1
     * add2
     * add3
     * add4
     * t2 结束
     * add5
     * add6
     * add7
     * add8
     * add9
     * 线程t1和t2结束
     *
     * 这种写法是可以完成功能的，t2先执行并阻塞，t1判断当lists对象为5个后，阻塞并释放锁，t2继续执行，打印日志并通知t1继续执行
     */
}