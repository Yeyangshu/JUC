/**
 * Copyright (C), 2018-2020
 * FileName: VolatileTest01
 * Author:   11077
 * Date:     2020/6/8 23:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc003;

import java.util.concurrent.TimeUnit;

/**
 * 不加volatile，死循环
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/8 23:20
 */
public class VolatileTest01 {
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
}
