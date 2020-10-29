package com.yeyangshu.juc.juc007;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 虚引用，jvm使用
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/21 18:57
 */
public class Reference05_PhantomReference {
    public static final List<Object> LIST = new LinkedList<>();
    public static final ReferenceQueue<Reference01_Finalize> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<Reference01_Finalize> phantomReference = new PhantomReference<>(new Reference01_Finalize(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 虚引用get不到
                System.out.println(phantomReference.get());
            }
        }).start();

        // 检测队列是否有值
        new Thread(() -> {
            while (true) {
                Reference<? extends Reference01_Finalize> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("虚引用对象被jvm回收了" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * null
     * finalize
     * null
     * null
     */
}