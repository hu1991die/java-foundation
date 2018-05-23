package com.feizi.java.concurrency.thread;

/**
 * Created by feizi on 2018/5/17.
 */
public class TestSleep {
    private int i = 10;
    private Object object = new Object();

    public static void main(String[] args) {
        /**
         * 线程： Thread-0开始执行，i的值为：11
         * 线程： Thread-0准备进入休眠状态...
         * 线程： Thread-0休眠结束...
         * 线程： Thread-0继续执行，i的值为===========>：12
         * 线程： Thread-1开始执行，i的值为：13
         * 线程： Thread-1准备进入休眠状态...
         * 线程： Thread-1休眠结束...
         * 线程： Thread-1继续执行，i的值为===========>：14
         * 结论：
         * 1、当Thread-0进入休眠状态，Thread-1并没有马上执行，而是等待Thread-0休眠结束释放了对象锁才继续执行
         * 2、当调用sleep()方法时，必须捕获异常或者向上抛出异常，当线程休眠结束并不会马上执行，而是进入就绪状态，等待CPU的再次调度，调用Sleep()方法相当于是进入了阻塞状态
         */

        TestSleep testSleep = new TestSleep();
        Thread t1 = testSleep.new MyTestThread();
        t1.start();

        Thread t2 = testSleep.new MyTestThread();
        t2.start();
    }

    class MyTestThread extends Thread{
        @Override
        public void run() {
            synchronized (object){
                i++;
                System.out.println("线程： " + Thread.currentThread().getName() + "开始执行，i的值为：" + i);
                System.out.println("线程： " + Thread.currentThread().getName() + "准备进入休眠状态...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("线程： " + Thread.currentThread().getName() + "休眠结束...");
                i++;
                System.out.println("线程： " + Thread.currentThread().getName() + "继续执行，i的值为===========>：" + i);
            }
        }
    }
}
