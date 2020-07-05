package com.yeyangshu.juc.juc009;

import java.util.concurrent.*;

/**
 * 返回结果并且可能抛出异常的任务。实现者定义了一个不带任何参数的叫做 call 的方法。
 * Callable 接口类似于 Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。但是 Runnable 不会返回结果，并且无法抛出经过检查的异常。
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/30 22:30
 */
public class T03_Callable {
    /**
     * V call() throws Exception;
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Callable";
            }
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(c);

        System.out.println(future.get());

        service.shutdown();
    }

}
