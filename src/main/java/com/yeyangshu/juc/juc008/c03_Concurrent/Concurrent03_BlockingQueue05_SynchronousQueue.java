package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue：数据同步交换的队列
 * 一个虚假的队列，因为它实际上没有真正用于存储元素的空间，每个插入操作都必须有对应的取出操作，没取出时无法继续放入。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/28 23:14
 */
public class Concurrent03_BlockingQueue05_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                // 取出操作
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 阻塞等待消费者消费
        strs.put("aaa");
        strs.put("bbb");
        //strs.add("aaa");
        System.out.println(strs.size());
    }

    /**
     * 情景一：一个添加，一个取值
     * strs.put("aaa");
     * 结果：
     * aaa
     * 0
     *
     * 情景二：两个添加，只有一个取值
     * strs.put("aaa");
     * strs.put("bbb");
     * 结果：
     * aaa
     * 阻塞。。。。
     */
}