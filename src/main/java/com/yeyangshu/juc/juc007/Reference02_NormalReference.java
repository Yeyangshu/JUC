package com.yeyangshu.juc.juc007;

import java.io.IOException;

/**
 * 普通引用，强引用
 *
 * 例：Object object = new Object();
 * 强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。
 * 当内存空间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/20 10:20
 */
public class Reference02_NormalReference {

    public static void main(String[] args) throws IOException {
        Reference01_Finalize reference01 = new Reference01_Finalize();
//        reference01 = null;
        // 显式调用垃圾回收尝试进行回收reference01
        System.gc();

        // gc是运行在别的线程，下面方法只是为了阻塞当前线程
        System.in.read();
    }

    /**
     * 测试情景一：关闭reference01 = null;
     * // reference01 = null;
     * 程序运行后控制台永远不会输出，Reference01_Finalize有reference01指向它，肯定不是垃圾，一定不会被回收
     *
     *
     * 测试情景二：打开reference01 = null;
     * 程序运行后控制台输出finalize，Reference01_Finalize没有引用指向它，是垃圾，被回收
     */
}