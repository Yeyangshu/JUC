package com.yeyangshu.juc.juc008.c04_review;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/29 23:06
 */
public class R1_LockSupport {
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.println(c);
                LockSupport.unpark(t2); //叫醒t2
                LockSupport.park();//t1阻塞
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        }, "t1");

        t1.start();
        t2.start();
    }
}
