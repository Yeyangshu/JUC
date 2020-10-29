package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写时复制
 * 当新增和删除元素时会创建一个新的数组，在新的数组中增加或者排除指定对象，最后用新增数组替换原来的数组。
 * 读操作不加锁，写（增、删、改）操作加锁
 * 写效率低，适用于读多写少
 * CopyOnWriteArrayList：并发版ArrayList
 * CopyOnWriteArraySet：并发版Set
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/26 11:56
 */
public class Concurrent02_CopyOnWrite {
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i = 0; i < ths.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a" + r.nextInt(100000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    private static void runAndComputeTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t -> t.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    /**
     *     copy原数组，length+1
     *     public boolean add(E e) {
     *         synchronized (lock) {
     *             Object[] es = getArray();
     *             int len = es.length;
     *             es = Arrays.copyOf(es, len + 1);
     *             es[len] = e;
     *             setArray(es);
     *             return true;
     *         }
     *     }
     *
     *     将引用指向新的数组
     *         final void setArray(Object[] a) {
     *         array = a;
     *     }
     */
}