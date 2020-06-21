/**
 * Copyright (C), 2018-2020
 * FileName: ThreadLocal01
 * Author:   11077
 * Date:     2020/6/14 10:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc007;

import java.util.concurrent.TimeUnit;

/**
 * 两个线程访问一个对象，对象被更改
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/14 10:44
 */
public class ThreadLocal01 {
    volatile static Person person = new Person();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(person.name);
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "李四";
        }).start();
    }

    /**
     * 李四
     */
}

class Person {
    String name = "张三";
}
