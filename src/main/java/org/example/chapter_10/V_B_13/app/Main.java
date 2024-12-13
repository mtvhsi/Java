package org.example.chapter_10.V_B_13.app;

import org.example.chapter_10.V_B_13.model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Разработчик Юматова А.С. Б762-2 13 Вариант");

        String fileName = "coffee_data.dat";
        TruckConnector connector = new TruckConnector(fileName);
        Truck truck = connector.getTruck();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Загрузить кофе");
            System.out.println("2. Показать кофе");
            System.out.println("3. Отсортировать кофе по цене за килограмм");
            System.out.println("4. Найти кофе по диапазону цен");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Введите название кофе: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите вес (кг): ");
                    double weight = scanner.nextDouble();
                    System.out.print("Введите цену (руб.): ");
                    double price = scanner.nextDouble();
                    System.out.print("Введите объем (л): ");
                    double volume = scanner.nextDouble();

                    // Выбор типа кофе
                    System.out.print("Выберите тип кофе (1 - молотый, 2 - растворимый): ");
                    int type = scanner.nextInt();

                    Coffee coffee;
                    if (type == 1) {
                        coffee = new GroundCoffee(name, weight, price, volume);
                    } else {
                        coffee = new InstantCoffee(name, weight, price, volume);
                    }
                    truck.loadCoffee(coffee);
                    connector.saveTruck(); // Сохранение нового состояния в файл
                    break;

                case 2:
                    System.out.println("Кофе в фургоне:");
                    truck.displayCoffee();
                    break;

                case 3:
                    truck.sortByPricePerWeight();
                    System.out.println("Кофе отсортировано по цене за килограмм.");
                    break;

                case 4:
                    System.out.print("Введите минимальную цену: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Введите максимальную цену: ");
                    double maxPrice = scanner.nextDouble();
                    System.out.println("Кофе в диапазоне цен:");
                    truck.getCoffeeInPriceRange(minPrice, maxPrice).forEach(System.out::println);
                    break;

                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Некорректный выбор. Попробуйте еще раз.");
            }
        }
    }
}
