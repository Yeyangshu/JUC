package com.yeyangshu.juc.juc007;

/**
 * 重写finalize
 * 垃圾回收时会调用此方法，目的是便于观察垃圾回收时不同引用的表现（只是为了观察，其余情况不应该重写）
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/20 10:18
 */
public class Reference01_Finalize {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}