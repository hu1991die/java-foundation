package com.feizi.java.concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程中断
 * Created by feizi on 2018/5/17.
 */
public class TestInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                //while在try包裹中，通过异常中断就可以退出run
                try {
                    /*while (true){
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛
                        TimeUnit.SECONDS.sleep(2);
                    }*/

                    while (!Thread.interrupted()){
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("线程被中断....");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("interrupt: " + interrupt);
                }

                /*while (true){
                    System.out.println("未被中断....");
                }*/

                /*while (true){
                    if(this.isInterrupted()){
                        System.out.println("线程中断...");
                        break;
                    }
                }
                System.out.println("已经跳出循环，线程中断....");*/
            }
        };

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
    }
}
