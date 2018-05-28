package com.feizi.java.concurrency.model.two;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by feizi on 2018/5/28.
 */
public class TestTwo {
    public static void main(String[] args) {
        Storage storage = new Storage();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            threadPool.execute(new Producer("生产者" + i, storage));
        }

        for (int i = 0; i < 2; i++){
            threadPool.execute(new Consumer("消费者" + i, storage));
        }

        threadPool.shutdown();
    }
}
