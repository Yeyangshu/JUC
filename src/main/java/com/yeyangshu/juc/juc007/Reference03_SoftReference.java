/**
 * Copyright (C), 2018-2020
 * FileName: Reference03_SoftReference
 * Author:   11077
 * Date:     2020/6/21 12:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc007;

import java.lang.ref.SoftReference;

/**
 * 软引用，做缓存用
 * 当一个对象被一个软引用指向的时候，只有系统内存不够用的时候才会回收它
 * m指向->SoftReference对象指向->字节数组
 * 设置虚拟机参数-Xms20M -Xmx20M，堆内存20M
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/21 12:11
 */
public class Reference03_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        // 再分配一个数组，heap装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }

    /**
     * 第一次控制台
     * [B@48140564
     * [B@48140564
     * [B@48140564
     * 堆内存够用
     *
     * 第二次控制台
     * [B@48140564
     * [B@48140564
     * null
     * 设置内存20M，又设置15M字节数组，堆内存不够，软引用被干掉
     */
}