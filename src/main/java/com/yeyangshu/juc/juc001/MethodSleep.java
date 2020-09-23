package com.yeyangshu.juc.juc001;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep方法，线程暂停一段时间
 * 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。
 */
public class MethodSleep {
    public static void main(String[] args) {
        String strDateFormat = "HH:mm:ss";
        Date startDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.format(startDate));
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 9) {
                    Date endDate = new Date();
                    System.out.println(sdf.format(endDate));
                }
            }
        }).start();
    }
}
