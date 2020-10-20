package com.yeyangshu.juc.juc004;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 10:46
 */
public class CasLock07_Exchanger {

    static Exchanger<String> exchanger = new Exchanger<>();

    /**
     * 第一个线程执行exchanger.exchange(s)后阻塞，等待第二个线程
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t1").start();

        /**
         * 第二个线程执行exchanger.exchange(s)后阻塞，等待第一个线程
         *
         * @param args
         */
        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "t2").start();
    }

    /**
     * t2 T1
     * t1 T2
     */
}