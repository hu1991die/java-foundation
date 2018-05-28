package com.feizi.java.concurrency.classical;

/**
 * 消费者抽象类
 * Created by feizi on 2018/5/28.
 */
public abstract class AbstractConsumer implements Consumer, Runnable{

    @Override
    public void run() {
        while (true){
            try {
                consume();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
