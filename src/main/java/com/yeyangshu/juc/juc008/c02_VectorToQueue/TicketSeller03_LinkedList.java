package com.yeyangshu.juc.juc008.c02_VectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
public class TicketSeller03_LinkedList {

    static List<String> tickets = new LinkedList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;

                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("销售了：" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }

    /**
     * 结果：
     * 销售了：票编号0
     * 销售了：票编号1
     * ...
     * 销售了：票编号995
     * 销售了：票编号996
     * 销售了：票编号997
     * 销售了：票编号998
     * 销售了：票编号999
     *
     * 加锁，没有出现异常，但是效率特别低
     */
}