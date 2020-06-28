package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/28 23:15
 */
public class Concurrent03_BlockingQueue06_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        //strs.put("aaa");


		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/

    }
}
