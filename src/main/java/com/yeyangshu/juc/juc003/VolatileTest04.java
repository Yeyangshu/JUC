package com.yeyangshu.juc.juc003;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * volatile关键字
 *
 * volatile能保证线程的可见性，不能保证原子性
 * 不加synchronize，count不能达到100000
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/8 23:20
 */
public class VolatileTest04 {

    /**
     * 启动线程数
     */
    private static final int COUNT_THREAD = 10;

    /**
     * 计算初始值
     */
    volatile int count = 0;

    /**
     * count++非原子操作
     */
    void m() {
        IntStream.range(0, 10000).forEach(i -> count++);
    }

    public static void main(String[] args) {
        VolatileTest04 test04 = new VolatileTest04();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < COUNT_THREAD; i++) {
            threads.add(new Thread(test04::m, "thread" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(test04.count);
    }

    /**
     * 结果：
     * 1 87796
     * 2 54954
     * 3 59780
     * 4 66903
     * 5 92548
     * count不能达到100000
     * 原因：count++不是原子操作，volatile能保证线程的可见性，不能替代synchronized，不能保证原子性
     */
}