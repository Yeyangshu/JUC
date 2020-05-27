package com.yeyangshu.juc.juc001;

/**
 * 实现线程的两种主要方式
 */
public class CreateThread {
    public static void main(String[] args) {
        //继承Thread类
        new MyThread().start();

        //实现Runnable接口线程启动方式
        new Thread(new MyRunnable()).start();
    }
}

class MyThread extends Thread {  //继承Thread类
    @Override
    public void run() {  //重写run()方法
        System.out.println("方法一：继承Thread创建线程");
    }
}

class MyRunnable implements Runnable {  //实现Runnable接口
    @Override
    public void run() {  //重写run()方法
        System.out.println("方法二：实现Runnable接口创建线程");
    }
}
