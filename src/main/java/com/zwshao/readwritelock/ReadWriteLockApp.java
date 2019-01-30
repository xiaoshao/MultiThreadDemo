package com.zwshao.readwritelock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteLockApp {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final SharedOperattion operattion = new SharedOperattion();
        for (int index = 0; index < 3; index++) {

            executorService.execute(new Runnable() {
                public void run() {
                    operattion.write(new Random().nextInt(1000));
                }
            });
        }
        for (int index = 0; index < 3; index++) {

            executorService.execute(new Runnable() {

                public void run() {
                    operattion.read();
                }
            });


        }

        executorService.shutdown();
    }
}
