package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lab8Sync {
    public static void main(String[] args) {
        int n = 2; // Количество потоков инкрементации
        int m = 2; // Количество потоков декрементации

        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(n + m);
        CountDownLatch latch = new CountDownLatch(n + m);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < n; i++) {
            executorService.execute(new IncrementTask(counter, latch));
        }

        for (int i = 0; i < m; i++) {
            executorService.execute(new DecrementTask(counter, latch));
        }

        try {
            // Ожидаем завершения всех потоков
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println("Final Counter Value: " + counter.getValue());
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
    }
}