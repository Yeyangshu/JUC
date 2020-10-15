package com.yeyangshu.juc.juc002;

/**
 * synchronized关键字
 * <p>
 * synchronized是可重入锁
 */
public class SynchronizedTest08 {

    /**
     * 同步方法m1
     */
    public synchronized void m1() {
        System.out.println("m1 start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    /**
     * 同步方法m2
     */
    private synchronized void m2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }


    public static void main(String[] args) {
        /**
         * 模拟重入锁
         * 一个方法调用另一个同步方法
         */
        new SynchronizedTest08().m1();

        /**
         * 模拟父子类
         * 子类调用父类的方法
         */
        new SynchronizedTest08Child().m1();
    }

    /**
     * new SynchronizedTest08().m1()结果
     * m1 start
     * m2
     * m1 end
     * 一个同步方法可以调用另一个同步方法，一个线程已经拥有了某个对象的锁，再次申请的时候仍然会得到该对象的锁
     *
     * new SynchronizedTest08Child().m1()结果
     * child start
     * m1 start
     * m2
     * m1 end
     * child end
     * 子类调用父类必须是可重入的，调用父类的是同一把锁
     */
}

/**
 * 模拟父子类
 */
class SynchronizedTest08Child extends SynchronizedTest08 {
    @Override
    public synchronized void m1() {
        System.out.println("child start");
        super.m1();
        System.out.println("child end");
    }
}