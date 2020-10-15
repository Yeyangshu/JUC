package com.yeyangshu.juc.juc002;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字
 * <p>
 * 案例：写方法加锁，读方法不加锁，脏读
 */
public class SynchronizedTest07 extends Thread {

    public static void main(String[] args) {

        Account account = new Account();

        new Thread(() -> account.set("yeyangshu", 100)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("account get balance = " + account.getBalance("yeyangshu"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("account get balance = " + account.getBalance("xiaoming"));

    }

    /**
     * 同步写线程睡眠2000ms，主线程进行读操作，金额为0，读到写线程中间状态的数据，产生脏读
     * 同步写线程写入数据2000ms后，主线程读，金额为100
     * account get balance = 0.0
     * account get balance = 100.0
     * 上述问题的产生就是同步方法和非同步方法是同时执行的
     * 解决方案：读方法加锁
     * 问题：加锁后效率低下
     */
}

/**
 * 银行账户
 */
class Account {

    /**
     * 账户名
     */
    String name;

    /**
     * 账户余额
     */
    double balance;

    /**
     * 同步写
     *
     * @param name
     * @param balance
     */
    synchronized void set(String name, double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    /**
     * @param name
     * @return
     */
    /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }
}