package com.yeyangshu.juc.juc009;

import java.util.concurrent.Executor;

/**
 * Executor接口，异步方式执行任务
 * 顶层接口Executor提供了一种思想：将任务提交和任务执行进行解耦。用户无需关注如何创建线程，如何调度线程来执行任务，用户只需提供Runnable对象，将任务的运行逻辑提交到执行器(Executor)中，由Executor框架完成线程的调配和任务的执行部分。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/29 23:39
 */
public interface T01_Executor {
    // 在未来某个时间执行给定的命令。该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
    void execute(Runnable command);
}