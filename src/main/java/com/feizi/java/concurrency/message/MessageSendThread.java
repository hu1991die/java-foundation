package com.feizi.java.concurrency.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 消息发送线程
 * Created by feizi on 2018/5/25.
 */
public class MessageSendThread extends Thread {
    private ConcurrentHashMap<Integer, String> messages;

    public MessageSendThread(ConcurrentHashMap messages) {
        this.messages = messages;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);

            while (this.messages.size() > 0){
                for (Map.Entry<Integer, String> message : this.messages.entrySet()){
                    System.out.println("消息id： " + message.getKey() + "暂未发送成功，在此重发： " + message.getValue());
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
