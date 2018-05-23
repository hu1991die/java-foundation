package com.feizi.java.concurrency.synchronsize;

/**
 * Created by feizi on 2018/5/17.
 */
public class AccountingSync implements Runnable {
    private static AccountingSync instance = new AccountingSync();

    /*共享资源（临界资源）*/
    static int i = 0;

    /**
     * 作用于静态方法，锁是当前class对象，也就是AccountingSync来所对应的class对象
     * 即无论传入多少个实例，使用的都是同一把锁，会发生互斥
     */
    public static synchronized void increaseStatic(){
        i++;
    }

    /**
     * 非静态方法，访问时锁（实例）不一样不会发生互斥（即如果传入的是同一个实例会发生互斥，不会产生线程安全问题，否则
     * 如果传入的是不同的实例，则不会发生互斥）
     */
    public synchronized void increaseNostatic(){
        i++;
    }

    /**
     * 使用this作为对象锁，锁住的是当前传入的实例对象，如果传入的是同一个实例，则不会有线程安全问题，否则会有线程安全问题
     */
    public void increaseThis(){
        //this,当前实例对象锁
        synchronized (this){
            i++;
        }
    }

    /**
     * 静态实例对象锁, 这种写法一般比较推荐，因为静态实例是属于类的，在JVM启动的时候就已经被加载了，
     * 所以无论传入多少个实例，使用的都是同一把对象锁
     */
    public void increaseStaticInstance(){
        synchronized (instance){
            i++;
        }
    }

    /**
     * class对象锁， 锁住的是当前的class对象, 这种方式同上面的静态实例对象锁的方式，无论传入多少个实例，使用的
     * 都是同一把对象锁
     */
    public void increaseClass(){
        synchronized (AccountingSync.class){
            i++;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 2000000; j++){
            increaseStatic();
//            increaseNostatic();
//            increaseThis();
//            increaseStaticInstance();
//            increaseClass();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*AccountingSync increase = new AccountingSync();

        Thread t1 = new Thread(increase);
        Thread t2 = new Thread(increase);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("结果i=" + i);*/

        Thread t1 = new Thread(new AccountingSync());
        Thread t2 = new Thread(new AccountingSync());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("结果i=" + i);
    }
}
