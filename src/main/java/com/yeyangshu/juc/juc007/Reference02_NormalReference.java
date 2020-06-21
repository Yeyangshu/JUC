/**
 * Copyright (C), 2018-2020
 * FileName: Reference02_NormalReference
 * Author:   11077
 * Date:     2020/6/20 10:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc007;

import java.io.IOException;

/**
 * 普通引用，强引用
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/20 10:20
 */
public class Reference02_NormalReference {
    public static void main(String[] args) throws IOException {
        Reference01_Finalize reference01 = new Reference01_Finalize();
        reference01 = null;
        // gc运行在别的线程需要阻塞住
        System.gc();

        // 阻塞线程
        System.in.read();
    }

    /**
     * //reference01 = null;
     * 控制台永远不会输出，Reference01_Finalize有reference01指向它，肯定不是垃圾，一定不会被回收
     *
     *
     * reference01 = null;
     * 控制台输出finalize，Reference01_Finalize没有引用指向它，是垃圾，被回收
     */
}
