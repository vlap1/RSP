package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class Lab8NoSync {
    public static void main(String[] args) {
        int n = 2; // Количество потоков инкрементации
        int m = 2; // Количество потоков декрементации

        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(n + m);//пул потоков с фиксированным количеством потоков, равным n + m.
        CountDownLatch latch = new CountDownLatch(n + m);//счетчик ожидания завершения потоков

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


