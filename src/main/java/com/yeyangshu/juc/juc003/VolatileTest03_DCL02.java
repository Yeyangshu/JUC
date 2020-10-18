package com.yeyangshu.juc.juc003;

import java.util.stream.IntStream;

/**
 * volatile关键字
 *
 * DCL单例，加volatile，对比VolatileTest02_DCL01
 */
public class VolatileTest03_DCL02 {

    private static volatile VolatileTest03_DCL02 INSTANCE;

    private VolatileTest03_DCL02 (){}

    public static VolatileTest03_DCL02 getSingleton() {
        // Single Checked
        if (INSTANCE == null) {
            synchronized (VolatileTest03_DCL02.class) {
                // Double Checked
                if (INSTANCE == null) {
                    INSTANCE = new VolatileTest03_DCL02();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        IntStream.range(1, 100).forEach(i -> new Thread(
                () -> System.out.println(VolatileTest02_DCL01.getSingleton().hashCode())
        ).start());
    }

    /**
     * 保证原子性和保证重排序是两回事
     *
     * 在volatile变量的赋值操作后面会有一个内存屏障（生成的汇编代码上），读操作不会被重排序到内存屏障之前。
     * 比如上面的例子，取操作必须在执行完1-2-3之后或者1-3-2之后，不存在执行到1-3然后取到值的情况。
     * 从「先行发生原则」的角度理解的话，就是对于一个volatile变量的写操作都先行发生于后面（时间上的先后顺序）对这个变量的读操作。
     */
}