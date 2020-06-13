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

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏
 *
 * 构造方法
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 *  -parties 是参与线程的个数
 *  -第二个构造方法的 Runnable 参数，意思是触发 barrier 时线程要执行的任务
 *
 * 方法
 * public int await() throws InterruptedException, BrokenBarrierException
 * public int await(long timeout, TimeUnit unit) throws InterruptedException, BrokenBarrierException, TimeoutException
 * 线程调用 await() 表示自己已经到达栅栏，会将自己阻塞还会将计数器减1
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 9:03
 */
public class CasLock03_CyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> System.out.println("满人"));

        for (int i = 0; i < 100; i++) {
            int threadI = i;
            new Thread(() -> {
                try {
                    System.out.println(threadI);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
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
