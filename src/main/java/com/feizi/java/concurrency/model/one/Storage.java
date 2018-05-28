package com.feizi.java.concurrency.model.one;

import java.util.LinkedList;

/**
 * wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，并且释放锁资源，使得自己处于等待（阻塞）状态，让其他线程执行。
 * notify()方法：当生产者/消费者向缓冲区中放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时释放锁资源，使得自己处于等待状态。
 * Created by feizi on 2018/5/28.
 */
public class Storage {
    //仓库最大存储量
    private static final int MAX_COUNT = 10;

    //仓库存储的载体
    private LinkedList<Object> list = new LinkedList<>();

    /**
     * 生产产品-同步方法
     * @param producer
     */
    public synchronized void produce(String producer){
        while (list.size() == MAX_COUNT){
            System.out.println("【仓库已满】，" + producer + "：暂时不能执行生产任务...");
            try {
                //仓库已满，暂不生产，生产阻塞
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //生产产品
        list.add(new Object());
        System.out.println( producer + "：=====》生产了一个产品\t，【仓库储量】为：" + list.size());
        notifyAll();
    }

    /**
     * 消费产品-同步方法
     * @param consumer
     */
    public synchronized void consume(String consumer){
        while (list.size() == 0){
            System.out.println("【仓库已空】， " + consumer + "：暂不消费...");
            try {
                //仓库已空，暂不消费，消费阻塞
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //消费产品
        list.remove();
        System.out.println(consumer + "：================》消费了一个产品\t，【仓库储量】为：" + list.size());
        notifyAll();
    }

    /**
     * 生产产品-同步块
     * @param producer
     */
    public void produce1(String producer){
        synchronized (list){
            while (list.size() == MAX_COUNT){
                try {
                    System.out.println("【仓库已满】，" + producer + "：暂时不能执行生产任务...");
                    //仓库已满，暂不生产，生产阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //生产产品
            list.add(new Object());
            System.out.println( producer + "：=====》生产了一个产品\t，【仓库储量】为：" + list.size());
            list.notifyAll();
        }
    }

    /**
     * 消费产品-同步块
     * @param consumer
     */
    public void consume1(String consumer){
        synchronized (list){
            while (list.size() == 0){
                try {
                    System.out.println("【仓库已空】， " + consumer + "：暂不消费...");
                    //仓库已空，暂不消费，消费阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费产品
            list.remove();
            System.out.println(consumer + "：================》消费了一个产品\t，【仓库储量】为：" + list.size());
            list.notifyAll();
        }
    }
}
