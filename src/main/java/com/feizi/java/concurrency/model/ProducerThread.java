package com.feizi.java.concurrency.model;

import java.util.concurrent.TimeUnit;

/**
 * 生产者线程
 * Created by feizi on 2018/5/17.
 */
public class ProducerThread implements Runnable{
    private Resource product;
    private String name;

    public ProducerThread(Resource product, String name) {
        this.product = product;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            product.produce(name);
        }
    }
}
