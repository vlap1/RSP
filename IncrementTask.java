package org.example;

import java.util.concurrent.CountDownLatch;

class IncrementTask implements Runnable {
    private Counter counter;
    private CountDownLatch latch;

    public IncrementTask(Counter counter, CountDownLatch latch) {
        this.counter = counter;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
        latch.countDown();
    }
}