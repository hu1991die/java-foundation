package com.feizi.java.concurrency.model;

/**
 * Created by feizi on 2018/5/17.
 */
public class TestThread {
    public static void main(String[] args) {
        Resource product = new Resource("馒头");

        //生产者线程启动
        new Thread(new ProducerThread(product, "线程1")).start();
        new Thread(new ProducerThread(product, "线程2")).start();

        //消费者线程启动
        new Thread(new ConsumeThread(product, "线程1")).start();
        new Thread(new ConsumeThread(product, "线程2")).start();
    }
}
