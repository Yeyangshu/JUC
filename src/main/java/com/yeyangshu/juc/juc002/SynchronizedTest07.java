package com.yeyangshu.juc.juc002;

import java.util.concurrent.TimeUnit;

/**
 * synchronized案例
 * 写方法加锁，读方法不加锁，脏读
 */
public class SynchronizedTest07 extends Thread {

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(() -> account.set("xiaoming", 100)).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("xiaoming"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("xiaoming"));


    }
}

class Account {
    String name;
    double balance;

    synchronized void set(String name, double balance) {
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }
}

