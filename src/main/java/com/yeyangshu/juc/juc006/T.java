/**
 * Copyright (C), 2018-2020
 * FileName: T
 * Author:   11077
 * Date:     2020/6/13 17:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yeyangshu.juc.juc006;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 17:15
 */
public class T {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();
    }
}
