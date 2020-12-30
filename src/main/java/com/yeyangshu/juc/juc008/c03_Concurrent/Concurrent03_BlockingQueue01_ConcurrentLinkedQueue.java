package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue：并发队列(基于链表)
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/27 10:03
 */
public class Concurrent03_BlockingQueue01_ConcurrentLinkedQueue {

    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            // 相当于add，返回boolean类型，添加成功返回true，添加失败返回false
            strs.offer("a" + i);
        }
        System.out.println(strs);

        System.out.println(strs.size());

        // 取值并且remove掉
        System.out.println(strs.poll());
        System.out.println(strs.size());

        // 取值不会remove掉
        System.out.println(strs.peek());
        System.out.println(strs.size());
    }

    /**
     * 结果：
     * [a0, a1, a2, a3, a4, a5, a6, a7, a8, a9]
     * 10
     *
     * a0
     * 9
     *
     * a1
     * 9
     */
}