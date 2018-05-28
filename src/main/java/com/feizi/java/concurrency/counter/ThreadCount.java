package com.feizi.java.concurrency.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by feizi on 2018/5/25.
 */
public class ThreadCount {
    public static void main(String[] args) {
        Thread[] threads = new Thread[15];
        for (int i = 0; i < 10; i++){
            threads[i] = new AThread();
            threads[i].start();
        }
    }
}

class AThread extends Thread{
    @Override
    public void run() {
        /*Counter counter = new Counter();
        System.out.println(Counter.calNum());*/

        /*Counter2 counter2 = new Counter2();
        System.out.println(counter2.calNum());*/

        /*System.out.println(MyCounter.calNum());*/

        System.out.println(MyCounter2.calNum());
    }
}

class Counter{
    private static long num;

    public Counter() {
        synchronized (Counter.class){
            num++;
        }
    }

    public static synchronized long calNum(){
        return num;
    }
}

class Counter2{
    private static long num;

    public synchronized long calNum(){
        num++;
        return num;
    }
}

class MyCounter{
    private static AtomicLong num = new AtomicLong();

    public static synchronized long calNum(){
        return num.incrementAndGet();
    }
}

class MyCounter2{
    private static volatile long num;

    public static synchronized long calNum(){
        num++;
        return num;
    }
}
