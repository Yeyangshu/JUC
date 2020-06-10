/**
 * Copyright (C), 2018-2020
 * FileName: AtomicTest01
 * Author:   11077
 * Date:     2020/6/9 23:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import com.yeyangshu.juc.juc003.VolatileTest05;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger使用，无需使用synchronized
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/9 23:17
 */
public class AtomicTest01 {
    AtomicInteger count = new AtomicInteger(0);

    void m() { // 原来方法上需要加synchronized
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet(); //=count++
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
}
