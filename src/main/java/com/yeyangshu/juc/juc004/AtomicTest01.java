package com.yeyangshu.juc.juc004;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS无锁优化
 *
 * AtomicInteger使用，无需使用synchronized
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/9 23:17
 */
public class AtomicTest01 {

    /**
     * AtomicInteger，线程安全的Integer
     */
    AtomicInteger count = new AtomicInteger(0);

    /**
     * 原来方法上需要加synchronized
     */
    void m() {
        for (int i = 0; i < 10000; i++) {
            // count.incrementAndGet = count++
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicTest01 atomicTest01 = new AtomicTest01();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(atomicTest01::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(atomicTest01.count);
    }

    /**
     * 结果：
     * 100000
     */
}