package com.feizi.java.concurrency.classical.one;

import com.feizi.java.concurrency.classical.AbstractConsumer;
import com.feizi.java.concurrency.classical.AbstractProducer;
import com.feizi.java.concurrency.classical.Consumer;
import com.feizi.java.concurrency.classical.Model;
import com.feizi.java.concurrency.classical.Producer;
import com.feizi.java.concurrency.classical.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by feizi on 2018/5/28.
 */
public class BlockingQueueModel implements Model {

    private final BlockingQueue<Task> queue;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        // LinkedBlockingQueue的队列是lazy-init的，但ArrayBlockingQueue在创建时就已经init
        this.queue = new LinkedBlockingQueue<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    /**
     * 消费者实现
     */
    private class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable{

        @Override
        public void consume() throws Exception {
            Task task = queue.take();
            //固定时间范围的消费，模拟相对稳定的服务器处理过程
            Thread.sleep(500 + (long)(Math.random() * 500));
            System.out.println("==========>consume: " + task.no);
        }
    }

    /**
     * 生产者实现
     */
    private class ProducerImpl extends AbstractProducer implements Producer, Runnable{

        @Override
        public void produce() throws Exception {
            //不定期生产，模拟随机的用户请求
            Thread.sleep((long)(Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            queue.put(task);
            System.out.println("===>produce: " + task.no);
        }
    }

    public static void main(String[] args) {
        Model model = new BlockingQueueModel(3);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 2; i ++){
            threadPool.execute(model.newRunnableConsumer());
        }

        for (int i = 0; i < 5; i++){
            threadPool.execute(model.newRunnableProducer());
        }
    }
}
