package com.yeyangshu.juc.juc009;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask
 * 既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 9:26
 */
public class T04_02_FutureTask {

    /**
     * 其中一个构造方法
     * FutureTask(Runnable runnable, V result)
     *   创建一个 FutureTask，一旦运行就执行给定的 Runnable，并安排成功完成时 get 返回给定的结果 。
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get());
    }
}