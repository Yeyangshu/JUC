package com.yeyangshu.juc.juc001;

/**
 * 查看线程运行状态
 * @author yeyangshu
 * @version 1.0
 * @date 2020/9/24 0:46
 */
public class ThreadState implements Runnable {
    /**
     * 使当前线程等待 0.5 秒或其它线程调用 notify() 或 notifyAll() 方法
     * @throws InterruptedException
     */
    public synchronized void waitForSeconds() throws InterruptedException {
        wait(500);
    }

    /**
     * 使当前线程永久等待，直到其它线程调用 notify() 或 notifyAll() 方法
     * @throws InterruptedException
     */
    public synchronized void waitForYears() throws InterruptedException {
        wait();
    }

    /**
     * 唤醒由调用 wait() 方法进入等待状态的线程
     * @throws InterruptedException
     */
    public synchronized void notifyNow() throws InterruptedException {
        notify();
    }

    @Override
    public void run() {
        try {
            waitForSeconds();
            waitForYears();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadState threadState = new ThreadState();

        Thread thread = new Thread(threadState);
        // 创建线程，输出新线程状态
        System.out.println("新建线程：" + thread.getState());

        thread.start();
        // 启动线程，输出新线程状态
        System.out.println("启动线程：" + thread.getState());

        Thread.sleep(100);
        // 当前线程休眠 0.1 秒，使新线程运行 waitForSeconds 方法，输出新线程状态
        System.out.println("计时等待：" + thread.getState());

        Thread.sleep(1000);
        // 当前线程休眠 1 秒，使新线程运行 waitForYears 方法，输出新线程状态
        System.out.println("等待线程：" + thread.getState());

        threadState.notifyNow();
        // 调用 notifyNow 方法，输出新线程状态
        System.out.println("唤醒线程：" + thread.getState());

        Thread.sleep(1000);
        // 当前线程休眠 1 秒，使新线程结束，输出新线程状态
        System.out.println("等待线程：" + thread.getState());

        /*
         * 新建线程：NEW
         * 启动线程：RUNNABLE
         * 计时等待：TIMED_WAITING
         * 等待线程：WAITING
         * 唤醒线程：TERMINATED
         * 等待线程：TERMINATED
         */
    }
}
