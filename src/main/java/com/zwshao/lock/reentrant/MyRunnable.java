package com.zwshao.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    public void run() {
        try {
            reentrantLock.lock();

            for (int index = 0; index < 10; index++) {
                System.out.println("hello " + name + "in the thread " + Thread.currentThread().getName());
                Thread.currentThread().sleep(1000L);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
