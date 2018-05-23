package com.feizi.java.concurrency.model;

/**
 * Created by feizi on 2018/5/18.
 */
public class Resource {
    /*资源名称*/
    private String name;
    /*资源个数*/
    private int count;
    /*资源标记*/
    private boolean flag = false;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 生产馒头
     */
    public synchronized void produce(String threadName){
        //先判断标记是否已经生产了，如果已经生产了，则等待消费
        while (flag){
            try {
                //让生产线程等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //开始生产一个馒头
        this.count++;
        System.out.println("生产者【" + threadName + "】======>生产第 " + this.count + " 个" + this.getName() + "...");
        //将资源标记为已生产
        this.flag = true;
        notify();
//        notifyAll();
    }

    /**
     * 消费馒头
     */
    public synchronized void consume(String threadName){
        while (!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者【" + threadName + "】*****************>消费第 " + this.count + " 个" + this.getName() + "...");
        //将资源标记位未生产
        this.flag = false;
        notify();
//        notifyAll();
    }
}
