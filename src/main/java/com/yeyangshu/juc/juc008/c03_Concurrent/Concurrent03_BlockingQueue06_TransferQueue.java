package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue：基于链表的数据交换队列
 * 实现了接口TransferQueue，通过transfer方法放入元素时，如果发现有线程在阻塞在取元素，会直接把这个元素给等待线程。
 * 如果没有人等着消费，那么会把这个元素放到队列尾部，并且此方法阻塞直到有人读取这个元素。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/28 23:15
 */
public class Concurrent03_BlockingQueue06_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        //strs.put("aaa");


      /*new Thread(() -> {
         try {
            System.out.println(strs.take());
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }).start();*/

    }
}