/**
 * Copyright (C), 2018-2020
 * FileName: Reference04_WeakReference
 * Author:   11077
 * Date:     2020/6/21 12:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc007;

import java.lang.ref.WeakReference;

/**
 * 弱引用，遇到gc就会回收，一般用在容器
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/21 12:32
 */
public class Reference04_WeakReference {
    public static void main(String[] args) {
        WeakReference<Reference01_Finalize> reference = new WeakReference<>(new Reference01_Finalize());

        System.out.println(reference.get());
        System.gc();
        System.out.println(reference.get());

        ThreadLocal<Reference01_Finalize> t1 = new ThreadLocal<>();
        t1.set(new Reference01_Finalize());
        t1.remove();
    }

}
