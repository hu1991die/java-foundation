package com.feizi.java.concurrency.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/30.
 */
public class CountDownLatchTest3 {

    public static void main(String[] args) throws InterruptedException {
        /*线程计数器*/
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new StepOneThread(latch));
        Thread t2 = new Thread(new StepTwoThread(latch));

        t1.start();
        t2.start();

        //调用await()阻塞当前线程，直至计数器清零才可继续执行
        latch.await();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("all steps has finished...");
    }
}

/**
 * 步骤一线程
 */
class StepOneThread implements Runnable{

    private CountDownLatch latch;

    public StepOneThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("step one has finished...");
            //模拟步骤一耗时操作
            TimeUnit.SECONDS.sleep(2);
            //步骤一完成计数器减一
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 步骤二线程
 */
class StepTwoThread implements Runnable{

    private CountDownLatch latch;

    public StepTwoThread(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            //模拟步骤二耗时操作
            TimeUnit.SECONDS.sleep(2);
            System.out.println("step two has finished...");
            //步骤二完成计数器减一
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

