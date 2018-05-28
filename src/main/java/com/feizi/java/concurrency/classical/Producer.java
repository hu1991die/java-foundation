package com.feizi.java.concurrency.classical;

/**
 * 生产者接口
 * Created by feizi on 2018/5/28.
 */
public interface Producer {
    /**
     * 生产产品
     * @throws Exception
     */
    void produce() throws Exception;
}
