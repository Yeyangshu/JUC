package com.yeyangshu.juc.juc009;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
 * 计算完成后只能使用 get 方法来获取结果，如有必要，计算完成前可以阻塞此方法。取消则由 cancel 方法来执行。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/30 23:35
 */
public class T04_01_Future {

    public interface Future<V> {
        // cancel方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false。
        boolean cancel(boolean mayInterruptIfRunning);

        // isCancelled方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回true。
        boolean isCancelled();

        // isDone方法表示任务是否已经完成，若任务完成，则返回true。
        boolean isDone();

        // get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回。
        V get() throws InterruptedException, ExecutionException;

        // get(long timeout, TimeUnit unit)用来获取执行结果，如果在指定时间内，还没获取到结果，就直接返回null。
        V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    }
}