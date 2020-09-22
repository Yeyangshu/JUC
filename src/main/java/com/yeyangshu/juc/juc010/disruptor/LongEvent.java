package com.yeyangshu.juc.juc010.disruptor;

/**
 * Event事件
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/5 15:49
 */
public class LongEvent {
    private long value;

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value + "}";
    }
}
