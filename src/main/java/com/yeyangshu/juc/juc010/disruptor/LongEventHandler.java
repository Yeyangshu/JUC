package com.yeyangshu.juc.juc010.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * Event消费者
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/5 15:56
 */
public class LongEventHandler implements EventHandler {
    public static long count = 0;

    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("[" + Thread.currentThread().getName() + "]" + event + "，序号：" + sequence);
    }
}
