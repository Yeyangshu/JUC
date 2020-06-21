/**
 * Copyright (C), 2018-2020
 * FileName: CasLock01_ReentrantLock04
 * Author:   11077
 * Date:     2020/6/11 23:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock设置为公平锁
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/11 23:40
 */
public class CasLock01_ReentrantLock05 extends Thread {
    /** 参数true，指定lock为公平锁 */
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        CasLock01_ReentrantLock05 r5 = new CasLock01_ReentrantLock05();
        Thread t1 = new Thread(r5);
        Thread t2 = new Thread(r5);
        t1.start();
        t2.start();
    }

    /**
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * Thread-1获得锁
     * Thread-2获得锁
     * .........
     */

}

