package org.example.lab3b.mi2;


import java.util.concurrent.Semaphore;

class Cook implements Runnable {
    private final Semaphore potSemaphore;
    private final Semaphore mealAvailable;
    private final int capacity;
    private int mealsRemaining = 0;

    public Cook(Semaphore potSemaphore, Semaphore mealAvailable, int mealsRemaining, int capacity) {
        this.potSemaphore = potSemaphore;
        this.mealAvailable = mealAvailable;
        this.mealsRemaining = mealsRemaining;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                potSemaphore.acquire(); // Повар ждет доступа к кастрюле
                while (mealsRemaining < capacity) {
                    mealsRemaining++;
                    System.out.println("Cook added portion " + mealsRemaining + " to the pot");
                    mealAvailable.release(); // Повар добавляет порцию и будит одного из дикарей
                }
                potSemaphore.release(); // Повар освобождает кастрюлю
                Thread.sleep(2000); // Повар готовит порцию
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}