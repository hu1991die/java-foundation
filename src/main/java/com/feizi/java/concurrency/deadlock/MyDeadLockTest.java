package com.feizi.java.concurrency.deadlock;
import java.util.concurrent.TimeUnit;

/**
 * 测试死锁：
 * 1、首先，leftLock线程代理类启动的时候，先获取left对象锁，之后休眠2秒
 * 2、接着，rightLock线程代理类启动的时候，先获取right对象锁，之后休眠2秒
 * 3、leftLock休眠结束后，需要先获取right对象锁才能继续执行，而此时right已被rightLock锁定
 * 4、rightLock休眠结束后，需要先获取left对象锁才能继续执行，而此时left已被leftLock锁定
 * 5、于是，leftLock和rightLock互相等待，都需要等待对方先释放锁从而获得锁资源继而才能继续往下执行，
 * 此时如果没有外力的作用是不可能做到的，因此导致了死锁的情况
 * Created by feizi on 2018/5/25.
 */
public class MyDeadLockTest {
    public static void main(String[] args) {
        MyDeadLock lock = new MyDeadLock();
        ProxyLeftLock leftLock = new ProxyLeftLock(lock);
        ProxyRightLock rightLock = new ProxyRightLock(lock);

        leftLock.start();
        rightLock.start();
    }
}

/**
 * 死锁例子
 */
class MyDeadLock {
    private final Object left = new Object();
    private final Object right = new Object();

    /**
     * 左边
     * @throws InterruptedException
     */
    public void left() throws InterruptedException {
        synchronized (left){
            TimeUnit.SECONDS.sleep(2);
            synchronized (right){
                System.out.println("左边...");
            }
        }
    }

    /**
     * 右边
     */
    public void right() throws InterruptedException {
        synchronized (right){
            TimeUnit.SECONDS.sleep(2);

            synchronized (left){
                System.out.println("右边...");
            }
        }
    }
}

/**
 * 多线程执行代理类-左边
 */
class ProxyLeftLock extends Thread{
    private MyDeadLock lock;

    public ProxyLeftLock(MyDeadLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.left();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 多线程执行代理类-右边
 */
class ProxyRightLock extends Thread {
    private MyDeadLock lock;

    public ProxyRightLock(MyDeadLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.right();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

