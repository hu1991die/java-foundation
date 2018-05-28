package com.feizi.java.concurrency.model.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by feizi on 2018/5/28.
 */
public class TestOne {
    public static void main(String[] args) {
        Storage storage = new Storage();
        /*for (int i = 0; i < 2; i++){
            new Thread(new Producer("生产者【" + i + "】", storage)).start();
        }

        for (int i = 0; i < 5; i++){
            new Thread(new Consumer("消费者【" + i + "】" , storage)).start();
        }*/

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++){
            threadPool.execute(new Producer("生产者【" + i + "】", storage));
        }

        for (int i = 0; i < 6; i++){
            threadPool.execute(new Consumer("消费者【" + i + "】" , storage));
        }

        threadPool.shutdown();
    }
}
