package com.feizi.java.concurrency.thread;

/**
 * Created by feizi on 2018/5/17.
 */
public class TestYield {
    public static void main(String[] args) {
        /**
         * 执行结果：
         * 名字： 线程1执行
         * 名字： 线程2执行
         * 线程2结果num： 1065788928计算耗时： 8毫秒
         * 线程1结果count： 1784293664计算耗时： 138毫秒
         * 结论：
         * 1、调用yield()方法是为了让当前线程让出CPU执行权限，从而可以让CPU去执行其他线程，它和sleep()方法类似同样是不会释放对象锁，
         * 但是yield()不会控制具体的交出CPU权限的时间，同时也只能让具有相同优先级的线程获得CPU执行时间的机会
         * 2、调用yield()方法并不会让当前线程进入阻塞状态，而只是进入就绪状态，只需要等待重新获取CPU的时间片，而Sleep()则会进入阻塞状态
         */
        MyYieldThread t = new MyYieldThread("线程1");
        t.start();

        MyYieldThread2 t2 = new MyYieldThread2("线程2");
        t2.start();
    }
}

class MyYieldThread extends Thread{
    private String name;

    public MyYieldThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("名字： " + name + "执行");
        long start = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 1000000; i++){
            count = count + (i + 1);
            Thread.yield();
        }

        long end = System.currentTimeMillis();
        System.out.println(name + "结果count： " + count + "计算耗时： " + (end - start) + "毫秒");
    }
}

class MyYieldThread2 extends Thread{
    private String name;

    public MyYieldThread2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("名字： " + name + "执行");
        long start = System.currentTimeMillis();
        int num = 0;
        for (int i = 0; i < 10000000; i++){
            num += i * 8;
        }
        long end = System.currentTimeMillis();
        System.out.println(name + "结果num： " + num + "计算耗时： " + (end - start) + "毫秒");
    }
}
