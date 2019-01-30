package com.zwshao.lock.condition;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConditionApp {
    public static void main(String[] args) {
        BoundedQueue queue = new BoundedQueue(100);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new RemoveRunnable(queue), 1, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new AddRunnable(queue), 1, 5, TimeUnit.SECONDS);

        try {
            scheduledExecutorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();
    }


    public static class RemoveRunnable implements Runnable {
        private BoundedQueue queue;

        public RemoveRunnable(BoundedQueue queue) {
            this.queue = queue;
        }

        public void run() {
            for (int index = 0; index < 10; index++) {
                queue.remove();
            }
        }
    }

    public static class AddRunnable implements Runnable {
        private BoundedQueue queue;

        public AddRunnable(BoundedQueue queue) {
            this.queue = queue;
        }

        public void run() {
            for (int index = 0; index < 10; index++) {
                queue.add(index);
            }
        }
    }
}
