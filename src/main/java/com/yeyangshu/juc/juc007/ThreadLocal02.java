package com.yeyangshu.juc.juc007;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * 
 * ThreadLocal提供了线程的局部变量，每个线程都可以通过 set() 和 get() 来对这个局部变量进行操作，但不会和其他线程的局部变量进行冲突，实现了线程的数据隔离。
 * 用途：Spring声明式事务，保证同一个connection
 *
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
        }, "t2").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        }, "t1").start();
    }

    static class Person {
        String name = "张三";
    }

    /**
     * 结果：
     * null
     * t1线程向tl中的map set一个对象，t2取自己线程里的map去取
     *
     * ThreadLocal源码
     * set()，设置value到当前线程的map
     *
     * public void set(T value) {
     *     // 拿到当前线程
     *     Thread t = Thread.currentThread();
     *     // 拿到ThreadLocalMap，map中的key是this(当前ThreadLocal对象)，value是设置的值
     *     ThreadLocalMap map = getMap(t);
     *     if (map != null)
     *         map.set(this, value);
     *     else
     *         createMap(t, value);
     * }
     *
     * Thread类
     * ThreadLocal.ThreadLocalMap threadLocals = null;
     * ThreadLocalMap getMap(Thread t) {
     *     return t.threadLocals;
     * }
     *
     * get()，当前线程的map
     * public T get() {
     *     Thread t = Thread.currentThread();
     *     ThreadLocalMap map = getMap(t);
     *     if (map != null) {
     *         ThreadLocalMap.Entry e = map.getEntry(this);
     *         if (e != null) {
     *             @SuppressWarnings("unchecked")
     *             T result = (T)e.value;
     *             return result;
     *         }
     *     }
     *     return setInitialValue();
     * }
     */
}