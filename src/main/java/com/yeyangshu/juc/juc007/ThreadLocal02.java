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
 * 使用ThreadLocal
 * 用途：Spring声明式事务，保证同一个connection
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/14 10:50
 */
public class ThreadLocal02 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }).start();
    }

    static class Person {
        String name = "张三";
    }

    /**
     * null
     * set()，当前线程的map
     *     public void set(T value) {
     *         Thread t = Thread.currentThread();
     *         ThreadLocalMap map = getMap(t);
     *         if (map != null) {
     *             map.set(this, value);
     *         } else {
     *             createMap(t, value);
     *         }
     *     }
     *
     *     Thread类
     *     ThreadLocal.ThreadLocalMap threadLocals = null;
     *     ThreadLocalMap getMap(Thread t) {
     *         return t.threadLocals;
     *     }
     *
     * get()，当前线程的map
     *     public T get() {
     *         Thread t = Thread.currentThread();
     *         ThreadLocalMap map = getMap(t);
     *         if (map != null) {
     *             ThreadLocalMap.Entry e = map.getEntry(this);
     *             if (e != null) {
     *                 @SuppressWarnings("unchecked")
     *                 T result = (T)e.value;
     *                 return result;
     *             }
     *         }
     *         return setInitialValue();
     *     }
     */
}


