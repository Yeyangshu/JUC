package com.yeyangshu.juc.juc009;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
 * 创建单个线程来执行任务，如果这个线程异常结束，会创建另一个线程来替代。
 * 能确保依照任务在队列中的顺序来执行（例如FIFO、LIFO、优先级）
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 10:50
 */
public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }

        service.shutdown();
    }
}
