package com.feizi.java.concurrency.model.three;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue是一个已经在内部实现了同步的阻塞队列，实现方式采用的是await()/signal()方法，
 * 可以在生成对象时指定容量大小，用于阻塞操作的是put()和take()方法
 * put()方法：类似于前面的生产者线程，容量达到最大时，自动阻塞
 * take()方法：类似于前面的消费者线程，容量为0时，启动阻塞
 * Created by feizi on 2018/5/28.
 */
public class Storage {
    //仓库最大存储量
    private static final int MAX_COUNT = 10;

    //仓库存储的载体
//    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    private ArrayBlockingQueue<Object> list = new ArrayBlockingQueue<>(10);

    /**
     * 生产产品
     * @param producer
     */
    public void produce(String producer){
        //如果仓库已满
        if(list.size() == MAX_COUNT){
            System.out.println("仓库已满，【" + producer + "】：暂时不能执行生产任务...");
        }

        try {
            //生产产品
            list.put(new Object());
            System.out.println("===>【" + producer + "】：生产了一个产品，\t【仓库储量】为：" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费产品
     * @param consumer
     */
    public void consume(String consumer){
        //如果仓库已空
        if (list.size() == 0){
            System.out.println("仓库已空，【" + consumer + "】：暂时不能执行消费任务...");
        }

        try {
            //消费产品
            list.take();
            System.out.println("================>【" + consumer + "】：消费了一个产品，\t【仓库储量】为：" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
