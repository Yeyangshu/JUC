package com.yeyangshu.juc.juc009;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * WorkStealingPool
 *
 * 工作窃取线程池
 * 该线程池维护足以支持给定并行度级别的线程，并可以使用多个队列来减少争用。
 * WorkStealingPool是并行处理任务的，并不能保证执行顺序
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 20:49
 */
public class T11_ForkJoinPool_WorkStealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        // Java虚拟机的处理器数量
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        // 由于产生的是精灵线程（守护线程、后台线程），主线程不阻塞没有输出
        System.in.read();
    }

    static class R implements Runnable {
        int time;

        public R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
    /**
     * 6
     * 1000 ForkJoinPool-1-worker-2
     * 2000 ForkJoinPool-1-worker-1
     * 2000 ForkJoinPool-1-worker-3
     * 2000 ForkJoinPool-1-worker-6
     * 2000 ForkJoinPool-1-worker-4
     * 2000 ForkJoinPool-1-worker-5
     * 2000 ForkJoinPool-1-worker-2
     */
}