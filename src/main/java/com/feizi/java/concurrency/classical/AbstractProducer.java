package com.feizi.java.concurrency.classical;

/**
 * 生产者抽象类
 * Created by feizi on 2018/5/28.
 */
public abstract class AbstractProducer implements Producer, Runnable {

    @Override
    public void run() {
        while (true){
            try {
                produce();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
