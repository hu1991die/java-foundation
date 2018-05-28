package com.feizi.java.concurrency.classical;

/**
 * 模型接口
 * Created by feizi on 2018/5/28.
 */
public interface Model {

    /**
     * 创建消费者
     * @return
     */
    Runnable newRunnableConsumer();

    /**
     * 创建生产者
     * @return
     */
    Runnable newRunnableProducer();
}
