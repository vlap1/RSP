package Lab_2;

import java.util.*;
import java.util.stream.Stream;

public class Lab_2 {

    ArrayList<User> arr = new ArrayList();

    public void initialize() {
        setupData();
        testResult();
    }

    private void setupData() {
        arr.add(new User("Юстиниан", "Бирюков", new Space()));
        arr.add(new User("Ефрем", "Жуков", new SeaCommunication()));
        arr.add(new User("Климент", "Анисимов", new Resort()));
        arr.add(new User("Аскольд", "Гусев", new Wind()));
        arr.add(new User("Архип", "Горбачёв", new SeaCommunication()));
        arr.add(new User("Аверьян", "Федосеев", new Resort()));
        arr.add(new User("Абрам", "Кошелев", new SeaCommunication()));
        arr.add(new User("Ефрем", "Корнилов", new Wind()));
        arr.add(new User("Игнатий", "Виноградов", new Space()));
        arr.add(new User("Роман", "Власов", new Resort()));
    }

    private void testResult() {
        printTitle("Общая численность клиентов");
        int count = arr.size();
        print(Integer.toString(count));

        printTitle("Общая численность клиентов по тарифу Волна");
        int countTarif = (int) arr.stream().filter(item -> item.checkTarif(Wind.class)).count();
        print(Integer.toString(countTarif));

        printTitle("Сортировка тарифов по абонентской плате");
        arr.forEach(item -> print(item.toString()));
        printTitle("Next");
        Stream<User> sorted = arr.stream().sorted((i1, i2) -> i1.compareTo(i2));
        sorted.forEach(item -> print(item.toString()));
    }

    private void printTitle(String text) {
        System.out.println("------------------------------------");
        System.out.println(text);
    }

    private void print(String text) {
        System.out.println(text);
    }
}

class User {
    private String name;
    private String surname;
    private Tarif tarif;

    public String toString() {
        return "Name: " + name + " Surname: " + surname + " Tarif: " + tarif.getClassName();
    }

    User(String name, String surname, Tarif tarif) {
        this.name = name;
        this.surname = surname;
        this.tarif = tarif;
    }

    Boolean checkTarif(Class<?> type) {
        return type == this.tarif.getClass();
    }

    int compareTo(User user) {
        if (this.tarif.cost < user.tarif.cost) {
            return 0;
        } else {
            return 1;
        }
    }
}