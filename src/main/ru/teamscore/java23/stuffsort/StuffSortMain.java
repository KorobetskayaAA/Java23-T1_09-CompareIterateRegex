package ru.teamscore.java23.stuffsort;

import java.util.*;

public class StuffSortMain {
    final static String[] namesMale = {"Иван", "Илья", "Роман", "Борис", "Александр", "Степан"};
    final static String[] surnamesMale = {"Кузнецов", "Иванов", "Петров", "Шварц", "Короленко", "Самарин"};
    final static String[] namesFemale = {"Ирина", "Анастасия", "Варвара", "Марина", "Мария", "Юлия"};
    final static String[] surnamesFemale = {"Кузнецова", "Иванова", "Петрова", "Шварц", "Короленко", "Самарина"};
    final static String[] positions = {"Разработчик", "Тестировщик", "Руководитель", "HR", "Бухгалтер", "Документовед"};
    final static Random rnd = new Random(100);
    final static List<Employee> stuff = new ArrayList<>();

    private static <T> T getRandomFromArray(T[] arr) {
        return arr[rnd.nextInt(arr.length)];
    }

    private static void setRandomName(Employee e) {
        if (rnd.nextDouble() < 0.5) {
            e.setName(getRandomFromArray(namesMale));
            e.setSurname(getRandomFromArray(surnamesMale));
        } else {
            e.setName(getRandomFromArray(namesFemale));
            e.setSurname(getRandomFromArray(surnamesFemale));
        }
    }

    private static String getRandomPosition() {
        return getRandomFromArray(positions);
    }

    private static int getRandomWage() {
        return rnd.nextInt(20000, 200000);
    }

    private static void printStuff() {
        for (var employee : stuff) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        for(int i = 0; i < 20; i++) {
            var employee = new Employee(UUID.randomUUID());
            setRandomName(employee);
            employee.setPosition(getRandomPosition());
            employee.setWage(getRandomWage());
            stuff.add(employee);
        }

        System.out.println("СПИСОК СОТРУДНИКОВ");
        printStuff();
        //stuff.sort();

        // По фамилии-имени
        stuff.sort(Comparator
                .comparing(Employee::getSurname)
                .thenComparing(Employee::getName)
        );
        System.out.println("СПИСОК СОТРУДНИКОВ по алфавиту");
        printStuff();

        // По убыванию з/п
        stuff.sort(Comparator
                .comparing(Employee::getWage)
                .reversed()
        );

        System.out.println("СПИСОК СОТРУДНИКОВ по з/п");
        printStuff();

        // По должности как в массиве, потом по фамилии по алфавиту
        Comparator<Employee> byPosition = (e1, e2) -> {
            var positionList = Arrays.asList(positions);
            int positionOrder1 = positionList.indexOf(e1.getPosition());
            int positionOrder2 = positionList.indexOf(e2.getPosition());
            return positionOrder1 - positionOrder2;
        };
        stuff.sort(byPosition
                .thenComparing(Employee::getSurname)
        );
        System.out.println("СПИСОК СОТРУДНИКОВ по должности");
        printStuff();
    }
}
