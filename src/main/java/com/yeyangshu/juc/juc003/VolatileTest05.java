package com.yeyangshu.juc.juc003;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * volatile关键字
 *
 * synchronize能保证线程的可见性，也能保证原子性
 * 加synchronize，count能达到100000
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/8 23:20
 */
public class VolatileTest05 {

    /**
     * 启动线程数
     */
    private static final int COUNT_THREAD = 10;

    /**
     * 计算初始值
     */
    volatile int count = 0;

    synchronized void m() {
        IntStream.range(0, 10000).forEach(i -> count++);
    }

    public static void main(String[] args) {
        VolatileTest05 test04 = new VolatileTest05();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
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
     * 100000
     */
}