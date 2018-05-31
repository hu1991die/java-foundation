package com.feizi.java.concurrency.tool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/29.
 */
public class ThreadLocalHolder {
    private static ThreadLocal<Integer> holder = new ThreadLocal<Integer>(){
        private Random rand = new Random(10);
        protected synchronized Integer initialValue(){
            return rand.nextInt(100);
        }
    };

    public static void increment(){
        holder.set(holder.get() + 1);
    }

    public static Integer get(){
        return holder.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            threadPool.execute(new Accessor(i));
        }
        threadPool.shutdown();
    }
}

class Accessor implements Runnable{
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadLocalHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#线程#" + id + " : " + ThreadLocalHolder.get();
    }
}
