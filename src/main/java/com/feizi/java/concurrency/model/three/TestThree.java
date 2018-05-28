package com.feizi.java.concurrency.model.three;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by feizi on 2018/5/28.
 */
public class TestThree {
    public static void main(String[] args) {
        Storage storage = new Storage();
        /*启动生产者线程*/
        new Thread(new Producer("生产者1", storage)).start();
        new Thread(new Producer("生产者2", storage)).start();
        new Thread(new Producer("生产者3", storage)).start();
        /*启动消费者线程*/
        new Thread(new Consumer("消费者1", storage)).start();
        new Thread(new Consumer("消费者2", storage)).start();
        new Thread(new Consumer("消费者3", storage)).start();
    }
}
