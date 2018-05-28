package com.feizi.java.concurrency.model.two;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await()和signal()的功能基本上和wait()/notify()类似，完全可以取代它们，但是它们和新引入的锁定机制Lock直接挂钩，具有更强的灵活性，
 * 通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全
 * Created by feizi on 2018/5/28.
 */
public class Storage {
    //仓库最大存储量
    private static final int MAX_COUNT = 10;

    //仓库存储的载体
    private LinkedList<Object> list = new LinkedList<>();

    //可重入锁
    private final Lock lock = new ReentrantLock();

    //仓库满的条件变量
    private final Condition full = lock.newCondition();

    //仓库空的条件变量
    private final Condition empty = lock.newCondition();

    /**
     * 生产产品
     * @param producer
     */
    public void produce(String producer){
        try {
            //获得锁
            lock.lock();
            while (list.size() == MAX_COUNT){
                try {
                    System.out.println("仓库已满，【" + producer + "】：暂时不能执行生产任务...");
                    //由于仓库已满，暂不生产，生产阻塞
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //生产产品
            list.add(new Object());

            System.out.println("===>【" + producer + "】：生产了一个产品，\t【仓库储量】为：" + list.size());

            empty.signalAll();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 消费产品
     * @param consumer
     */
    public void consume(String consumer){
        try {
            //获得锁
            lock.lock();

            while (list.size() == 0){
                try {
                    System.out.println("仓库已空，【" + consumer + "】：暂时不能执行消费任务...");
                    //由于仓库已空，暂不消费，消费阻塞
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费产品
            list.remove();

            System.out.println("=============>【" + consumer + "】：消费了一个产品，\t【仓库储量】为：" + list.size());
            full.signalAll();
        }finally {
            //释放锁
            lock.unlock();
        }
    }
}
