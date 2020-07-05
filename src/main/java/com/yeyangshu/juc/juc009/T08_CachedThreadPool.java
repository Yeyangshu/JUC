package com.yeyangshu.juc.juc009;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个可缓存的线程池
 * 如果线程池的规模超过了处理需求时，将回收空闲的线程，
 * 而当需求增加时，则可以添加新的线程，线程池的规模不存在任何限制。
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 15:57
 */
public class T08_CachedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        TimeUnit.SECONDS.sleep(80);
        System.out.println(service);
    }

    /**
     * java.util.concurrent.ThreadPoolExecutor@5b464ce8[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]
     * java.util.concurrent.ThreadPoolExecutor@5b464ce8[Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]
     * pool-1-thread-2
     * pool-1-thread-1
     */
}
