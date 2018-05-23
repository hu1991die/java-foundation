package com.feizi.java.concurrency.model;

import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/17.
 */
public class ConsumeThread implements Runnable {
    private Resource product;
    private String name;

    public ConsumeThread(Resource product, String name) {
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
            product.consume(name);
        }
    }
}
