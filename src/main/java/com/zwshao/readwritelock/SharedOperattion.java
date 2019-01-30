package com.zwshao.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedOperattion {

    private volatile Integer target;

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock();

        try {
            System.out.println("获得读锁" + Thread.currentThread().getName() + " " + target);
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(Integer target) {
        lock.writeLock().lock();
        try {
            this.target = target;

            System.out.println("获得写锁" + Thread.currentThread().getName() + " " + target);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
