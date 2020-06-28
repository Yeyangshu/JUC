package com.yeyangshu.juc.juc008.c03_Concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 和CopyOnWrite作对比
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/27 9:59
 */
public class Concurrent02_SynchronizedList {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> strsSync = Collections.synchronizedList(strs);
    }
}
