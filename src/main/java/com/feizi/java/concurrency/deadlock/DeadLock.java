package com.feizi.java.concurrency.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * 线程死锁
 * Created by feizi on 2018/5/22.
 */
public class DeadLock implements Runnable {
    public boolean flag = false;
    //静态对象是类的所有实例共享的
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        System.out.println("============flag: " + flag);

        if(flag){
            System.out.println("即将获取o1对象的锁");
            synchronized (o1){
                try {
                    System.out.println("获取o1对象的锁....");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o2){
                    System.out.println("获取o2对象的锁....");
                    System.out.println("=====true=====");
                }
            }
        }

        if(!flag){
            System.out.println("=============》即将获取o2对象的锁");
            synchronized (o2){
                try {
                    System.out.println("============>获取o2对象的锁....");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (o1){
                    System.out.println("============>获取o1对象的锁....");
                    System.out.println("=====false=====");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock d1 = new DeadLock();
        DeadLock d2 = new DeadLock();
        d1.flag = true;
        d2.flag = false;

        new Thread(d1).start();
        new Thread(d2).start();
    }
}
