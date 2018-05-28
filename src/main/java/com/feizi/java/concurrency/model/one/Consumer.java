package com.feizi.java.concurrency.model.one;

import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/28.
 */
public class Consumer implements Runnable {
    /*消费者名称*/
    private String name;
    /*仓库*/
    private Storage storage;

    public Consumer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(2500);
                storage.consume(name);
//                storage.consume1(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
