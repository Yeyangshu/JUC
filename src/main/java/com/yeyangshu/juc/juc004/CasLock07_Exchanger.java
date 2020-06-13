/**
 * Copyright (C), 2018-2020
 * FileName: CasLock07_Exchanger
 * Author:   11077
 * Date:     2020/6/13 10:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc004;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 10:46
 */
public class CasLock07_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

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
