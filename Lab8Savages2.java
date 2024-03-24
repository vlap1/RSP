package org.example.lab3b.mi2;

import java.util.concurrent.Semaphore;
public class Lab8Savages2 {
    public static void main(String[] args) {
        int numberOfSavages = 10; // Количество дикарей
        int capacityOfPot = 5;   // Вместимость кастрюли

        Semaphore potSemaphore = new Semaphore(1); // Семафор для кастрюли
        Semaphore mealAvailable = new Semaphore(0); // Семафор для доступности порций
        int mealsRemaining = 0; // Количество доступных порций

        // Создаем и запускаем потоки дикарей
        for (int i = 0; i < numberOfSavages; i++) {
            Thread savage = new Thread(new Savage(potSemaphore, mealAvailable, mealsRemaining, i + 1));
            savage.start();
        }

        // Создаем и запускаем поток повара
        Thread cook = new Thread(new Cook(potSemaphore, mealAvailable, mealsRemaining, capacityOfPot));
        cook.start();
    }
}