package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界，指定数量
 * 基于数组实现的可阻塞队列，构造时必须制定数组大小，往里面放东西时如果数组满了便会阻塞直到有位置（也支持直接返回和超时等待），通过一个锁ReentrantLock保证线程安全。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/27 11:35
 */
public class Concurrent03_BlockingQueue03_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

//        strs.put("aaa");
//        strs.add("aaa");
        strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
    }
    /**
     * strs.put("aaa");
     * 无输出，程序阻塞
     *
     * strs.add("aaa");报异常
     * Exception in thread "main" java.lang.IllegalStateException: Queue full
     *
     * strs.offer("aaa", 1, TimeUnit.SECONDS);
     * [a0, a1, a2, a3, a4, a5, a6, a7, a8, a9]
     * 使用返回值判断是否成功
     */
}