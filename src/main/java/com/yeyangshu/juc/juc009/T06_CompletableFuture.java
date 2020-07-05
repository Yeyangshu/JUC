package com.yeyangshu.juc.juc009;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/4 9:34
 */
public class T06_CompletableFuture {
    public static void main(String[] args) {
        long start, end;
        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(() -> priceOfTB());
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(() -> priceOfJD());

        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();

        CompletableFuture.supplyAsync(() -> priceOfTM())
                .thenApply(String::valueOf).thenApply(str -> "price " +str).thenAccept(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("use completable future! " + (end - start));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }
    private static double priceOfTB() {
        delay();
        return 2.00;
    }
    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
