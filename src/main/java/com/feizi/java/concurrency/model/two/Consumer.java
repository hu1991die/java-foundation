package com.feizi.java.concurrency.model.two;

import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/28.
 */
public class Consumer implements Runnable {
    private String name;

    private Storage storage;

    public Consumer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(2);
                storage.consume(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
