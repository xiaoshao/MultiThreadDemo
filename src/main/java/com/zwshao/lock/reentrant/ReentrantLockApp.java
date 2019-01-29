package com.zwshao.lock.reentrant;

public class ReentrantLockApp {

    public static void main(String[] args){
        Thread first = new Thread(new MyRunnable("first"), "first");

        first.start();

        try {
            Thread.currentThread().sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread secondThread = new Thread(new MyRunnable("second"), "second");

        secondThread.start();
    }
}
