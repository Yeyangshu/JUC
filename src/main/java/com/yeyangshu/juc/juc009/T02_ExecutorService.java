package com.yeyangshu.juc.juc009;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorService，解决执行服务的生命周期问题，添加了管理生命周期的方法
 * ExecutorService生命周期有三种状态：运行、关闭、已终止，ExecutorService初建时处于运行状态。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/29 23:43
 */
public interface T02_ExecutorService extends T01_Executor {

    // shutdown方法执行平缓的关闭过程：启动一次顺序关闭，执行以前提交（包括未执行）的任务，但不接受新任务。
    void shutdown();

    // shutdownNow方法执行粗暴的关闭过程：试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
    List<Runnable> shutdownNow();

    // 如果此执行程序已关闭，则返回 true。
    boolean isShutdown();

    // 如果关闭后所有任务都已完成，则返回 true。
    boolean isTerminated();

    // 请求关闭、发生超时或者当前线程中断，无论哪一个首先发生之后，都将导致阻塞，直到所有任务完成执行。
    boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;

    // 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的 Future。
    <T> Future<T> submit(Callable<T> task);

    // 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
    <T> Future<T> submit(Runnable task, T result);

    // 提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
    Future<?> submit(Runnable task);

    // 执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException;

    // 执行给定的任务，当所有任务完成或超时期满时（无论哪个首先发生），返回保持任务状态和结果的 Future 列表。
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException;

    // 执行给定的任务，如果某个任务已成功完成（也就是未抛出异常），则返回其结果。
    <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;

    // 执行给定的任务，如果在给定的超时期满前某个任务已成功完成（也就是未抛出异常），则返回其结果。
    <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
}