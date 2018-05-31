package com.feizi.java.concurrency.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/30.
 */
public class CountDownLatchTest {
    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //步骤一完成
                System.out.println("step one has finished...");
                try {
                    //模拟步骤一耗时操作
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //步骤一完成，调用countDown()方法，计数器就减1
                latch.countDown();

                //步骤二完成
                System.out.println("step two has finished...");
                try {
                    //模拟步骤二耗时操作
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //步骤二完成，调用countDown()方法，计数器就减1
                latch.countDown();
            }
        }).start();

        //步骤一和步骤二完成之前会阻塞住
        latch.await();

        //直到所有的步骤都完成，主线程才继续执行
        System.out.println("all steps have finished...");
    }
}
