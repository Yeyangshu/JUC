package com.yeyangshu.juc.juc007;

import java.lang.ref.WeakReference;

/**
 * 弱引用，遇到gc就会回收，一般用在容器
 * 如果有一个强引用指向了弱引用，只要强引用消失，弱引用也会消失
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/21 12:32
 */
public class Reference04_WeakReference {

    public static void main(String[] args) {
        // reference指向new WeakReference<>弱引用对象，其里面有弱引用指向new Reference01_Finalize()对象
        WeakReference<Reference01_Finalize> reference = new WeakReference<>(new Reference01_Finalize());

        System.out.println(reference.get());
        // 回收掉new Reference01_Finalize()对象
        System.gc();
        System.out.println(reference.get());

        // 弱引用最典型应用：ThreadLocal
        ThreadLocal<Reference01_Finalize> tl = new ThreadLocal<>();
        // tl强引用 指向 ThreadLocal对象
        // tl里的map里的key弱引用 指向 ThreadLocal对象
        tl.set(new Reference01_Finalize());
        // ThreadLocal里面的对象不需要的时候必须要remove掉
        tl.remove();
    }

    /**
     * 结果：强引用消失，弱引用也会消失
     * com.yeyangshu.juc.juc007.Reference01_Finalize@45ee12a7
     * null
     *
     * finalize
     */

}