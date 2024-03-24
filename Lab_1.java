package Lab_1;

import java.util.ArrayList;
import java.util.Calendar;

public class Lab_1 {

    Database<Bus> db = new Database();

    public static void main(String arg[]) throws Exception {
        Lab_1 o = new Lab_1();
        o.setupData();
        o.testResult();
    }

    void setupData() {
        db.add("Фамилиевич", "Олег", "Отчество", 1, 12, "Opel", 2000, 100);
        db.add("Фамилиевич", "Игорь", "Отчество", 2, 67, "Nissan", 2003, 200);
        db.add("Фамилиевич", "Никита", "Отчество", 3, 34, "Volkswagen", 2005, 300);
        db.add("Фамилиевич", "Матвей", "Отчество", 4, 45, "Ford", 2020, 400);
        db.add("Фамилиевич", "Егор", "Отчество", 4, 56, "Tesla", 2020, 500);
        db.add("Фамилиевич", "Сергей", "Отчество", 5, 67, "No brenk", 2019, 600);
        db.add("Фамилиевич", "Коля", "Отчество", 7, 78, "No brenk", 1975, 120);
        db.add("Фамилиевич", "Андрей", "Отчество", 8, 34, "No brenk", 1980, 340);
        db.add("Фамилиевич", "Максим", "Отчество", 9, 14, "No brenk", 2, 550);
        db.add("Фамилиевич", "Владислав", "Отчество", 10, 67, "No brenk", 2022, 1960);
    }

    void testResult() {
        System.out.println("Cписок автобусов для заданного номера маршрута");
        print(db.getRouteId(67));

        System.out.println("Cписок автобусов, которые эксплуатируются больше заданного срока");
        print(db.getCarYear(2000));

        System.out.println("Cписок автобусов, пробег у которых больше заданного расстояния");
        print(db.getMileage(500));
    }

    private void print(ArrayList<Bus> arr) {
        for (Bus item : arr) {
            System.out.println("Имя: " + item.name + " " + "Фамилия: " + item.surname + " " + "Идентификатор: " + item.busId);
        }
        System.out.println();
    }
}

class Bus {
    String surname;
    String name;
    String middleName;
    int busId;
    int routeId;
    String carBrend;
    int carYear;
    int mileage;
}

class Database<T> {
    ArrayList<T> arr = new ArrayList();

    void add(
            String surname,
            String name,
            String middleName,
            int busId,
            int routeId,
            String carBrend,
            int carYear,
            int mileage
    ) {
        Bus bus = new Bus();

        bus.name = name;
        bus.surname = surname;
        bus.middleName = middleName;
        bus.busId = busId;
        bus.routeId = routeId;
        bus.carBrend = carBrend;
        bus.carYear = carYear;
        bus.mileage = mileage;

        arr.add((T) bus);
    }

    ArrayList<T> getRouteId(int routeId) {
        ArrayList<T> res = new ArrayList();
        for (T item : arr) {
            Bus b = (Bus) item;
            if(b.routeId == routeId) {
                res.add((T) item);
            }
        }
        return res;
    }

    ArrayList<T> getCarYear(int carYear) {
        ArrayList<T> res = new ArrayList();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        for (T item : arr) {
            Bus b = (Bus) item;
            if(b.carYear > carYear) {
                res.add((T) item);
            }
        }
        return res;
    }

    ArrayList<T> getMileage(int mileage) {
        ArrayList<T> res = new ArrayList();
        for (T item : arr) {
            Bus b = (Bus) item;
            if(b.mileage > mileage) {
                res.add((T) item);
            }
        }
        return res;
    }
}