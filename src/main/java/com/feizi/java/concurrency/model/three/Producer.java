package com.feizi.java.concurrency.model.three;

import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/28.
 */
public class Producer implements Runnable {
    private String name;
    private Storage storage;

    public Producer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
                storage.produce(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
