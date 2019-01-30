package com.zwshao.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue {

    private Integer[] items;
    private int count;
    private int addIndex;
    private int removeIndex;

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Integer[size];
    }

    public void add(Integer item) {
        lock.lock();
        try {
            if (count == items.length) {
                notFull.awaitUninterruptibly();
            }

            items[addIndex] = item;

            System.out.println("Thread " + Thread.currentThread().getName() + " inserted item " + item + " at " + addIndex);

            if (++addIndex == items.length) {
                addIndex = 0;
            }

            count++;

            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public Integer remove() {
        lock.lock();

        try {
            if (count == 0) {
                notEmpty.awaitUninterruptibly();
            }

            int removedItem = items[removeIndex];

            System.out.println("Thread " + Thread.currentThread().getName() + " removed item " + removedItem + " at " + removeIndex);

            if (++removeIndex == items.length) {
                removeIndex = 0;
            }

            notFull.signalAll();

            return removedItem;
        } finally {
            lock.unlock();
        }
    }
}
