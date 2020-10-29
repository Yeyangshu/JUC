package com.yeyangshu.juc.juc008.c02_VectorToQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟程序：
 * N张火车票，每张票有一个编号，同时有10个窗口对外售票
 *
 * 程序可能出现的问题？
 * 重复销售？超量销售？
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/25 21:56
 */
public class TicketSeller01_ArrayList {

    static List<String> tickets = new ArrayList<>();

    /**
     * 向tickets里添加10000张票
     */
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    /**
     * 在tickets取票，取过之后remove()
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了：" + tickets.remove(0));
                }
            }).start();
        }
    }

    /**
     * 结果：
     * 销售了：票编号9519
     * 销售了：票编号9999
     * Exception in thread "Thread-5" Exception in thread "Thread-4" java.lang.ArrayIndexOutOfBoundsException: -1
     *     at java.util.ArrayList.remove(ArrayList.java:505)
     *     at com.yeyangshu.juc.juc008.c02_VectorToQueue.TicketSeller01_ArrayList.lambda$main$0(TicketSeller01_ArrayList.java:39)
     *     at java.lang.Thread.run(Thread.java:748)
     * java.lang.ArrayIndexOutOfBoundsException: -2
     *     at java.util.ArrayList.remove(ArrayList.java:505)
     *     at com.yeyangshu.juc.juc008.c02_VectorToQueue.TicketSeller01_ArrayList.lambda$main$0(TicketSeller01_ArrayList.java:39)
     *     at java.lang.Thread.run(Thread.java:748)
     *
     *  最后一张票可能会有多个线程共同抢一张票，会出现超卖的现象、没有加锁，线程不安全
     */
}