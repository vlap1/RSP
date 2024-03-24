package org.example.lab3b;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Lab8Savages1 {
    public static void main(String[] args) {
        int numberOfSavages = 10; // Количество дикарей
        int capacityOfPot = 5;   // Вместимость кастрюли

        BlockingQueue<Integer> pot = new ArrayBlockingQueue<>(capacityOfPot);// блокирующую очередь  которая используется для представления кастрюли с пищей

        // Создаем и запускаем поток повара
        Thread cook = new Thread(new Cook(pot));
        cook.start();

        // Создаем и запускаем потоки дикарей
        for (int i = 0; i < numberOfSavages; i++) {
            Thread savage = new Thread(new Savage(pot, i + 1));
            savage.start();
        }
    }
}