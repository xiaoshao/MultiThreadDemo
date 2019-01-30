package com.zwshao.future;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureApp {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();

        ExecutorService executorService = Executors.newCachedThreadPool();

        Future future = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {

                Thread.sleep(1000L);
                return 100;
            }
        });

        executorService.shutdown();

        try {
            Thread.sleep(7000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            System.out.println(future.get());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        LocalDateTime after = LocalDateTime.now();

        Duration duration = Duration.between(now, after);
        System.out.println("" + duration.getNano());
    }
}
