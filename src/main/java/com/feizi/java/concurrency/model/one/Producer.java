package com.feizi.java.concurrency.model.one;

import java.util.concurrent.TimeUnit;

/**
 * Created by feizi on 2018/5/28.
 */
public class Producer implements Runnable {
    /*生产者名称*/
    private String name;
    /*仓库*/
    private Storage storage;

    public Producer(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            try {
                storage.produce(name);
//                storage.produce1(name);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
