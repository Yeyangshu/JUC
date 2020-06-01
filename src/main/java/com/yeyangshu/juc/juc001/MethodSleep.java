package com.yeyangshu.juc.juc001;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep方法，线程暂停一段时间
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
