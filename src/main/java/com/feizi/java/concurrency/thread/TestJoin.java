package com.feizi.java.concurrency.thread;

/**
 * Created by feizi on 2018/5/17.
 */
public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 执行结果：
         * main主线程： main ====> 0
         * main主线程： main ====> 1
         * 当前线程： 线程1 ===> 0
         * main主线程： main ====> 2
         * 当前线程： 线程1 ===> 1
         * 当前线程： 线程1 ===> 2
         * 当前线程： 线程1 ===> 3
         * 当前线程： 线程1 ===> 4
         * main主线程： main ====> 3
         * main主线程： main ====> 4
         * 当前线程： 线程2 ===> 0
         * 当前线程： 线程2 ===> 1
         * 当前线程： 线程2 ===> 2
         * 当前线程： 线程2 ===> 3
         * 当前线程： 线程2 ===> 4
         * main主线程： main ====> 5
         * main主线程： main ====> 6
         * main主线程： main ====> 7
         * main主线程： main ====> 8
         * main主线程： main ====> 9
         * 结论：
         * 1、使用了join()方法之后，主线程会等待子线程结束之后才会结束
         */
        Thread t1 = new MyJoinThread("线程1");
        t1.start();
//        t1.join();

        for (int i = 0; i < 10; i++){
            if(i == 5){
                Thread t2 = new MyJoinThread("线程2");
                t2.start();
                t2.join();
            }
            System.out.println("main主线程： " + Thread.currentThread().getName() + " ====> " + i);
        }
    }
}

class MyJoinThread extends Thread{
    public MyJoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println("当前线程： " + Thread.currentThread().getName() + " ===> " + i);
        }
    }
}
