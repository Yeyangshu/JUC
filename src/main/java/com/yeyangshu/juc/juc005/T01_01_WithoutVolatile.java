package com.yeyangshu.juc.juc005;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题一：提供两个方法add、size，写两个线程
 * 线程一，添加10个元素到容器
 * 线程二，实时监控元素个数，当个数到5个时，线程二给出提示并结束
 */
public class T01_01_WithoutVolatile {

    List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T01_01_WithoutVolatile t = new T01_01_WithoutVolatile();

        /**
         * 线程一
         * 集合添加元素
         */
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.add(new Object());
                System.out.println("add" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        /**
         * 线程二
         * 监控集合大小
         */
        new Thread(() -> {
            while (true) {
                if (t.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }

    /**
     * 设计思路
     * t1线程向list集合添加元素，t2写while循环监控集合元素个数
     *
     * 结果：
     * add0
     * add1
     * add2
     * add3
     * add4
     * add5
     * add6
     * add7
     * add8
     * add9
     * 线程阻塞。。。
     *
     * 最终没有按照预期执行
     * 1.案例没有同步
     * 2.t.size()无法检测到数量，数据在两个线程之间是不可见的
     */
}