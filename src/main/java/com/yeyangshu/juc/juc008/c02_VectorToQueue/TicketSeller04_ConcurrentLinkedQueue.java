/**
 * Copyright (C), 2018-2020
 * FileName: TicketSeller04_ConcurrentLinkedQueue
 * Author:   11077
 * Date:     2020/6/25 22:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc008.c02_VectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 多线程少考虑list，多考虑Concurrent开头的
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/25 22:23
 */
public class TicketSeller04_ConcurrentLinkedQueue {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) {
                        break;
                    } else {
                        System.out.println("销售了：" + s);
                    }
                }
            }).start();
        }
    }
}
