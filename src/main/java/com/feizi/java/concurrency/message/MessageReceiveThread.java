package com.feizi.java.concurrency.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 消息接收线程
 * Created by feizi on 2018/5/25.
 */
public class MessageReceiveThread extends Thread {
    private ConcurrentHashMap<Integer, String> messages;

    public MessageReceiveThread(ConcurrentHashMap messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++){
                TimeUnit.SECONDS.sleep(1);
                for (Map.Entry<Integer, String> message : this.messages.entrySet()){
                    if(message.getKey() == i){
                        System.out.println("成功接收到id为： " + message.getKey() + "的消息： " + message.getValue());
                        this.messages.remove(message.getKey());
                    }
                }
                System.out.println("内存对象中的元素个数为： " + this.messages.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
