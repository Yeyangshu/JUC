package com.yeyangshu.juc.juc009;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FixedThreadPool
 * 可以并行处理任务
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 19:17
 */
public class T09_FixedThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        calculate();
        parallelCalculate();
    }

    /**
     * 1942
     * 564
     */

    /**
     * 顺序计算
     *
     * @return
     */
    public static void calculate() {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 并行计算
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void parallelCalculate() throws ExecutionException, InterruptedException {
        final int cpuCoreNum = 6;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        long start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }


    static class MyTask implements Callable<List<Integer>> {

        /**
         * 起始位置
         */
        int startPos;

        /**
         * 结束位置
         */
        int endPos;

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(startPos, endPos);
        }

        public MyTask(int startPos, int endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }
    }

    /**
     * 计算范围中的质数
     *
     * @param start 起始位置
     * @param end   结束位置
     * @return List<Integer> results
     */
    private static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (isPrime(i)) {
                results.add(i);
            }
        }
        return results;
    }

    /**
     * 判断是否为质数
     *
     * @param num 数
     * @return 布尔值
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}