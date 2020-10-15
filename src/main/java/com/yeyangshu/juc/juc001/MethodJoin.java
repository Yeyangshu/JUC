package com.yeyangshu.juc.juc001;

import java.util.Date;

/**
 * join 方法，thread2 等待 thread1 执行完毕才去执行
 * 等待该线程终止。
 */
public class MethodJoin {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("firstThread " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println("secondThread " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    /**
     * firstThread 0
     * firstThread 1
     * firstThread 2
     * firstThread 3
     * firstThread 4
     * firstThread 5
     * firstThread 6
     * firstThread 7
     * firstThread 8
     * firstThread 9
     * secondThread 0
     * secondThread 1
     * secondThread 2
     * secondThread 3
     * secondThread 4
     * secondThread 5
     * secondThread 6
     * secondThread 7
     * secondThread 8
     * secondThread 9
     */
}
