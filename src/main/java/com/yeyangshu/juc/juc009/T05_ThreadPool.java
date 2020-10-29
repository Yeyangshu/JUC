package com.yeyangshu.juc.juc009;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 9:49
 */
public class T05_ThreadPool {

    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" + "i=" + i + '}';
        }
    }

    public static void main(String[] args) {

        // 通过ThreadPoolExecutor的方式创建线程池，并使用guava自定义线程池名字
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4), new ThreadFactoryBuilder().setNameFormat("simple-thread-pool-%d").build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 向线程池添加8个任务
        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        // 打印剩下的任务队列
        System.out.println(tpe.getQueue());
        tpe.execute(new Task(100));
        System.out.println(tpe.getQueue())
        ;
        tpe.shutdown();
    }
}