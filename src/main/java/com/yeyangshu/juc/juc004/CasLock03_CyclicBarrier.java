/**
 * Copyright (C), 2018-2020
 * FileName: CasLock03_CyclicBarrier
 * Author:   11077
 * Date:     2020/6/13 9:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import java.io.Writer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 * <p>
 * 构造方法
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 * -parties 是参与线程的个数
 * -第二个构造方法的 Runnable 参数，意思是触发 barrier 时线程要执行的任务
 * <p>
 * 方法
 * public int await() throws InterruptedException, BrokenBarrierException
 * public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException
 * 线程调用 await() 表示自己已经到达栅栏，会将自己阻塞还会将计数器减1
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 9:03
 */
public class CasLock03_CyclicBarrier {

    public static void main(String[] args) {

        /**
         * CyclicBarrier使用
         */
//        test1();

        /**
         * CyclicBarrier模拟实际场景
         */
        test2();

    }

    /**
     * CyclicBarrier简单使用
     */
    private static void test1() {
        /**
         * 初始化参与线程的个数为20
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人"));

        for (int i = 0; i < 100; i++) {
            int threadI = i;
            new Thread(() -> {
                try {
                    System.out.println(threadI);
                    // 当前线程调用栅栏的await()方法，会将自己阻塞，计数器减1
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        /**
         * 0
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         * 10
         * 11
         * 12
         * 13
         * 14
         * 15
         * 16
         * 17
         * 18
         * 19
         * 满人
         * 20
         * 21
         * 22
         * 23
         * 24
         * 25
         * 26
         * 27
         * 28
         * 29
         * 30
         * 31
         * 32
         * 33
         * 34
         * 35
         * 36
         * 96
         * 94
         * 91
         * 满人
         * 90
         * 86
         * 85
         * 97
         * 99
         * 83
         * 98
         * 81
         * 82
         * 84
         * 79
         * 78
         * 77
         * 87
         * 76
         * 75
         * 88
         * 73
         * 89
         * 93
         * 满人
         * 92
         * 69
         * 70
         * 95
         * 71
         * 68
         * 72
         * 66
         * 74
         * 65
         * 80
         * 63
         * 64
         * 62
         * 59
         * 60
         * 61
         * 58
         * 57
         * 56
         * 55
         * 67
         * 37
         * 38
         * 39
         * 40
         * 41
         * 42
         * 43
         * 44
         * 45
         * 满人
         * 47
         * 46
         * 48
         * 49
         * 51
         * 50
         * 52
         * 53
         * 54
         * 满人
         */
    }

    /**
     * CyclicBarrier模拟实际场景
     * 复杂操作，需要访问数据库、网络和文件等
     * 第一种方式是顺序执行，效率非常低
     * 第二种方式是并发操作，分别执行不同的操作，必须三个线程全部执行完毕才去执行下一步
     */
    private static void test2() {
        int N = 4;

        CyclicBarrier barrier = new CyclicBarrier(N, () ->
                System.out.println("所有操作执行完毕，当前线程" + Thread.currentThread().getName())
        );

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }

        /**
         * 线程Thread-0正在写入数据...
         * 线程Thread-1正在写入数据...
         * 线程Thread-2正在写入数据...
         * 线程Thread-3正在写入数据...
         * 线程Thread-2写入数据完毕，等待其他线程写入完毕
         * 线程Thread-3写入数据完毕，等待其他线程写入完毕
         * 线程Thread-0写入数据完毕，等待其他线程写入完毕
         * 线程Thread-1写入数据完毕，等待其他线程写入完毕
         * 所有操作执行完毕，当前线程Thread-0
         * 所有线程写入完毕，继续处理其他任务...
         * 所有线程写入完毕，继续处理其他任务...
         * 所有线程写入完毕，继续处理其他任务...
         * 所有线程写入完毕，继续处理其他任务...
         */
    }


    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                // 线程睡眠来模拟写入数据操作
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
