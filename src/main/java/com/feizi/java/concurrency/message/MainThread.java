package com.feizi.java.concurrency.message;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 入口
 * Created by feizi on 2018/5/25.
 */
public class MainThread {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<Integer, String> messages = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++){
            messages.put(i, "第一波消息发送，消息ID: " + i);
        }

        System.out.println(messages.toString());

        Thread sendThread = new MessageSendThread(messages);
        Thread receiveThread = new MessageReceiveThread(messages);
        sendThread.start();
        receiveThread.start();
//        sendThread.join();
//        receiveThread.join();

        /*for (int i = 10; i < 20; i++){
            messages.put(i, "又一波消息到来，消息ID： " + i);
        }*/
    }
}
