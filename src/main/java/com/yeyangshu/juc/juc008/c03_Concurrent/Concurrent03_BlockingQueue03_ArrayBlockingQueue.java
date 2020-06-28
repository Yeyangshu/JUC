package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界，指定数量
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
     * 使用返回只判断是否成功
     */
}
