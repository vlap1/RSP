package org.example.lab3b.mi2;

import java.util.concurrent.Semaphore;

class Savage implements Runnable {
    private final Semaphore potSemaphore;
    private final Semaphore mealAvailable;
    private final int id;
    private int mealsEaten = 0;

    public Savage(Semaphore potSemaphore, Semaphore mealAvailable, int mealsRemaining, int id) {
        this.potSemaphore = potSemaphore;
        this.mealAvailable = mealAvailable;
        this.id = id;
        this.mealsEaten = mealsRemaining;
    }

    @Override
    public void run() {
        while (true) {
            try {
                potSemaphore.acquire(); // Дикарь ждет доступа к кастрюле
                if (mealsEaten > 0) {
                    System.out.println("Savage " + id + " is eating portion " + mealsEaten);
                    mealsEaten--;
                } else {
                    System.out.println("Savage " + id + " woke up the cook and is waiting for a portion");
                    mealAvailable.release(); // Дикарь будит повара
                    mealAvailable.acquire(); // Дикарь ждет доступности порции
                    System.out.println("Savage " + id + " is eating portion " + mealsEaten);
                    mealsEaten--;
                }
                potSemaphore.release(); // Дикарь освобождает кастрюлю
                Thread.sleep(1000); // Дикарь ест порцию
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}