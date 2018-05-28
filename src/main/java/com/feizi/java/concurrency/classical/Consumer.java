package com.feizi.java.concurrency.classical;

/**
 * 消费者接口
 * Created by feizi on 2018/5/28.
 */
public interface Consumer {
    /**
     * 消费产品
     * @throws Exception
     */
    void consume() throws Exception;
}
