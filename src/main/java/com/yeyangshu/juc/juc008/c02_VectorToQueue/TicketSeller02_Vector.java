/**
 * Copyright (C), 2018-2020
 * FileName: TicketSeller01_ArrayList
 * Author:   11077
 * Date:     2020/6/25 21:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc008.c02_VectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 模拟程序：
 * N张火车票，每张票有一个编号，同时有10个窗口对外售票
 *
 * 程序可能出现的问题？
 * 重复销售？超量销售？
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/25 21:56
 */
public class TicketSeller02_Vector {
    static List<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("销售了：" + tickets.remove(0));
                }
            }).start();
        }
    }

    /**
     * Exception in thread "Thread-7" Exception in thread "Thread-0" Exception in thread "Thread-6" Exception in thread "Thread-8" Exception in thread "Thread-4" Exception in thread "Thread-2" Exception in thread "Thread-3" Exception in thread "Thread-1" Exception in thread "Thread-9" java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
     * 	at java.base/java.util.Vector.remove(Vector.java:874)
     * 	at com.yeyangshu.juc.juc008.c02_VectorToQueue.TicketSeller02_Vector.lambda$main$0(TicketSeller02_Vector.java:48)
     * 	at java.base/java.lang.Thread.run(Thread.java:834)
     * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
     * 	at java.base/java.util.Vector.remove(Vector.java:874)
     * 	at com.yeyangshu.juc.juc008.c02_VectorToQueue.TicketSeller02_Vector.lambda$main$0(TicketSeller02_Vector.java:48)
     * 	at java.base/java.lang.Thread.run(Thread.java:834)
     * java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 0
     * ..............................
     *
     * 原因：tickets.size()加锁，tickets.remove加锁，但是两个方法中间没加锁，还是会超卖
     */
}
