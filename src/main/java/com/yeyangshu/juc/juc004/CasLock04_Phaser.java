package com.yeyangshu.juc.juc004;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser，阶段器
 *
 * 解决控制多个线程分阶段共同完成任务的情景问题
 *
 * Phaser内有2个重要状态，分别是phase和party
 * phase就是阶段，初值为0，当所有的线程执行完本轮任务，同时开始下一轮任务时，意味着当前阶段已结束，进入到下一阶段，phase的值自动加1。
 * party就是线程，party=7就意味着Phaser对象当前管理着7个线程
 * @author yeyangshu
 * @version 1.0
 * @date 2020/6/13 9:20
 */
public class CasLock04_Phaser {

    static Random r = new Random();

    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        // 批量增加parties的数量，管理7个线程
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承Phaser，重写onAdvance方法
     */
    static class MarriagePhaser extends Phaser {

        /**
         * 在每个阶段执行完成后回调的方法
         * 1.当每一个阶段执行完毕，此方法会被自动调用，重载此方法写入的代码会在每个阶段执行完毕时执行
         * 2.当此方法返回true时，意味着Phaser被终止
         *
         * @param phase
         * @param registeredParties
         * @return
         */
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    /**
     * person类，实现Runnable接口，具有四种行为
     * arriveAndAwaitAdvance，Arrives at this phaser and awaits others
     */
    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.println("%s 到达现场！" + name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.println("%s 吃完！" + name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.println("%s 离开！" + name);
            phaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println("%s 洞房 " + name);
                phaser.arriveAndAwaitAdvance();
            } else {
                // 到达，并注销一个parties数量，非阻塞方法
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    /**
     * %s 到达现场！新娘
     * %s 到达现场！p1
     * %s 到达现场！p0
     * %s 到达现场！p3
     * %s 到达现场！p4
     * %s 到达现场！新郎
     * %s 到达现场！p2
     * 所有人都到齐了！7
     *
     * %s 吃完！p3
     * %s 吃完！p0
     * %s 吃完！p1
     * %s 吃完！p2
     * %s 吃完！新郎
     * %s 吃完！p4
     * %s 吃完！新娘
     * 所有人都吃完了！7
     *
     * %s 离开！p4
     * %s 离开！p0
     * %s 离开！p3
     * %s 离开！新郎
     * %s 离开！p1
     * %s 离开！p2
     * %s 离开！新娘
     * 所有人都离开了！7
     *
     * %s 洞房 新娘
     * %s 洞房 新郎
     * 婚礼结束！新郎新娘抱抱！2
     */
}