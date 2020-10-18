package com.yeyangshu.juc.juc003;

import java.util.stream.IntStream;

/**
 * volatile关键字
 *
 * DCL单例，不加volatile，对比VolatileTest03_DCL02
 */
public class VolatileTest02_DCL01 {

    private static VolatileTest02_DCL01 INSTANCE;

    private VolatileTest02_DCL01 (){}

    public static VolatileTest02_DCL01 getSingleton() {
        // Single Checked
        if (INSTANCE == null) {
            synchronized (VolatileTest02_DCL01.class) {
                // Double Checked
                if (INSTANCE == null) {
                    INSTANCE = new VolatileTest02_DCL01();
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
     * 结果：
     * 734407977
     * 734407977
     * 734407977
     * 734407977
     * 看起来结果是正常的，但是还是有问题的
     * 主要在于INSTANCE = new VolatileTest02_DCL01();这句，这并非是一个原子操作，事实上经过JVM编译后的指令分为三步：
     * 1.给INSTANCE分配内存
     * 2.调用VolatileTest02_DCL01的构造函数来初始化成员变量
     * 3.将INSTANCE对象指向分配的内存空间（执行完这步INSTANCE就为非null了）
     *
     * 但是在JVM的即时编译器中存在指令重排序的优化。也就是说上面的第二步和第三步的顺序是不能保证的，最终的执行顺序可能是1-2-3，也可能是1-3-2。
     * 如果是1-3-2，则在3执行完毕，2未执行之前，被线程二抢占了，这时instance已经是非null了（但却没有初始化），所以线程二会直接返回INSTANCE，使用时就会报错。
     *
     */
}