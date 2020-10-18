package com.yeyangshu.juc.juc003;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字
 *
 * 不加volatile，死循环
 * A、B线程都用到一个变量，A线程工作空间保留了一份主内存的copy，B线程修改该变量，A线程未必知道
 * 线程之间不可见，volatile可以保证线程可见性
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/8 23:20
 */
public class VolatileTest01 {

    /**
     * 状态标记
     */
    volatile boolean running = true;

    void m() {
        System.out.println("m start");
        while (running) {

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        VolatileTest01 test01 = new VolatileTest01();
        new Thread(test01::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test01.running = false;
    }

    /**
     * 情况一：不加volatile
     * boolean running = true;
     * 结果：
     * m start
     * 程序出现死循环
     *
     * 情况二：加volatile
     * volatile boolean running = true;
     * 结果：
     * m start
     * m end
     *
     * Java内存模型（即Java Memory Model，简称JMM）中规定了所有的变量都存储在主内存中，每条线程还有自己的工作内存（栈空间），
     * 线程的工作内存中保存了该线程使用到的变量到主内存副本拷贝，线程对变量的所有操作（读取、赋值）都必须在工作内存中进行，
     * 而不能直接读写主内存中的变量。不同线程之间无法直接访问对方工作内存中的变量，线程间通信（变量值的传递）均需要在主内存来完成。
     */
}