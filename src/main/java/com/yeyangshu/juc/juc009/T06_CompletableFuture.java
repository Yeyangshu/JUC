package com.yeyangshu.juc.juc009;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 * 可以组合不同的任务
 *
 * 模拟爬虫去不同商城找商品价格并使用多线程进行比价
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 9:34
 */
public class T06_CompletableFuture {

    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();

        // 创建异步执行任务，取商城查找商品价格，supplyAsync可以支持返回值。
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfJD);

        // allOf：返回一个新的CompletableFuture，当所有给定的CompletableFutures完成时完成
        // join：进行汇总
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

        // 当一个线程依赖另一个线程的结果时，可以使用thenApply方法来把这两个线程串行化。
        CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf).thenApply(str -> "price " + str).thenAccept(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 天猫价格
     *
     * @return
     */
    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    /**
     * 淘宝价格
     *
     * @return
     */
    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    /**
     * 京东价格
     *
     * @return
     */
    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    /**
     * 模拟去商城获取价格所用的时间
     */
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

    /**
     * After 170 sleep!
     * After 207 sleep!
     * After 356 sleep!
     * use completable future! 424
     * After 17 sleep!
     * price 1.0
     */
}