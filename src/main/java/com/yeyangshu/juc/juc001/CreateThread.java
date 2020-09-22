package com.yeyangshu.juc.juc001;

import java.util.concurrent.*;

/**
 * 实现线程的几种主要方式
 */
public class CreateThread {
    public static void main(String[] args) {
        // 继承Thread类方式
        new MyThread().start();

        // 实现Runnable接口线程启动方式
        new Thread(new MyRunnable()).start();

        // FutureTask + Callable
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // 线程池
        new MyThreadPoolExecutor().executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("方法四：线程池");
                }
            }
        );
    }
}

/**
 * 继承 Thread 类，重写 run() 方法
 */
class MyThread extends Thread {
    @Override
    public void run() {  // 重写run()方法
        System.out.println("方法一：继承Thread创建线程");
    }
}

/**
 * 实现 Runnable 接口，重写 run() 方法
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {  //重写run()方法
        System.out.println("方法二：实现Runnable接口创建线程");
    }
}

/**
 * FutureTask + Callable 接口，重写 call() 方法
 */
class MyCallable implements Callable<String> { // 实现 Callable 接口
    @Override
    public String call() throws Exception {
        return "方法三：实现Callable接口创建线程";
    }
}

/**
 * FutureTask + Callable 接口，重写 call() 方法
 */
class MyThreadPoolExecutor {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(4), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());
}
