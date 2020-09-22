package com.yeyangshu.juc.juc010.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Event工厂
 * @author yeyangshu
 * @version 1.0
 * @date 2020/7/5 15:52
 */
public class LongEventFactory implements EventFactory {

    @Override
    public Object newInstance() {
        return new LongEvent();
    }
}
