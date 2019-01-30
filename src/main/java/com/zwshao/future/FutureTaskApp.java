package com.zwshao.future;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FutureTaskApp {

    public static void main(String[] args) {

        LocalDateTime before = LocalDateTime.now();

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000L);
                return 200;
            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(task);
        executorService.shutdown();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LocalDateTime after = LocalDateTime.now();

        Duration duration = Duration.between(before, after);

        System.out.println("costs " + duration.getNano());
    }
}
