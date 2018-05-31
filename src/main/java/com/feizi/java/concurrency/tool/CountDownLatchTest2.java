package com.feizi.java.concurrency.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/30.
 */
public class CountDownLatchTest2 {
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("step one has finished...");
                    TimeUnit.SECONDS.sleep(1);

                    //计数器减一
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("step two has finished...");

                    //计数器减一
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        //计数器清零之前，阻塞住当前线程
        latch.await();

        System.out.println("all steps have finished...");
    }
}
