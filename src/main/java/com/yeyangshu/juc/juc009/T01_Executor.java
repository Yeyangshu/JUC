package com.yeyangshu.juc.juc009;

import java.util.concurrent.Executor;

/**
 * Executor接口，异步方式执行任务
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/29 23:39
 */
public interface T01_Executor {
    // 在未来某个时间执行给定的命令。该命令可能在新的线程、已入池的线程或者正调用的线程中执行，这由 Executor 实现决定。
    void execute(Runnable command);
}
