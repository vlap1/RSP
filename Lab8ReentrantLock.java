package org.example;
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Lab8ReentrantLock {
    public static void main(String[] args) {
        int[] threadCounts = {1, 2, 4, 8};
        long[] executionTimes = new long[threadCounts.length];

        for (int i = 0; i < threadCounts.length; i++) {
            int threadCount = threadCounts[i];

            Counter counter = new Counter();
            ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
            CountDownLatch latch = new CountDownLatch(threadCount);

            long startTime = System.currentTimeMillis();

            for (int j = 0; j < threadCount; j++) {
                executorService.execute(new IncrementTask(counter, latch));
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executorService.shutdown();
            long endTime = System.currentTimeMillis();
            executionTimes[i] = endTime - startTime;
            System.out.println("Threads: " + threadCount + ", Final Counter Value: " + counter.getValue());
        }

        // Записываем результаты в файл Lab8.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("Lab8.txt"))) {
            writer.println("Thread Count\tExecution Time (ms)");
            for (int i = 0; i < threadCounts.length; i++) {
                writer.println(threadCounts[i] + "\t" + executionTimes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
