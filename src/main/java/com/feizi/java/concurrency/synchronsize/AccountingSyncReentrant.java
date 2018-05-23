package com.feizi.java.concurrency.synchronsize;

/**
 * Synchronsized的可重入特性
 * Created by feizi on 2018/5/17.
 */
public class AccountingSyncReentrant implements Runnable{
    static AccountingSyncReentrant instance = new AccountingSyncReentrant();

    /*共享资源（临界资源）*/
    static int i = 0;
    /*共享资源（临界资源）*/
    static int j = 0;

    @Override
    public void run() {
        for (int k = 0; k < 1000000; k++){
            synchronized (this){
                i++;
                increase();
            }
        }
    }

    public synchronized void increase(){
        j++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        //调用join()方法是为了让main主线程等待子线程执行完再执行
        t1.join();
        t2.join();
        System.out.println("结果： i=" + i + ", j=" + j);
    }
}
