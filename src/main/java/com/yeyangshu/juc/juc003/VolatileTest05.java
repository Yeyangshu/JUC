/**
 * Copyright (C), 2018-2020
 * FileName: VolatileTest01
 * Author:   11077
 * Date:     2020/6/8 23:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc003;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronize能保证线程的可见性，也能保证原子性
 * 加synchronize，count能达到100000
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/8 23:20
 */
public class VolatileTest05 {
    int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
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
}
