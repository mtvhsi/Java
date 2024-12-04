package org.example.chapter_6;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Разработчик: Юматова А.С. Б762/2");

        Scanner scanner = new Scanner(System.in);

        City city = new City("Россия", 1703, 12000000, 877, "Санкт-Петербург", 50000000, "Александр Беглов", "Русский");
        Village village = new Village("Моя Деревня", 1000, 100000, "Иванов И.И.");
        Hamlet hamlet = new Hamlet("Мой Хутор", 100, 20000);

        boolean running = true;

        while (running) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Управление городом");
            System.out.println("2. Управление деревней");
            System.out.println("3. Управление хутором");
            System.out.println("0. Выход");

            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    manageCity(scanner, city);
                    break;
                case 2:
                    manageVillage(scanner, village);
                    break;
                case 3:
                    manageHamlet(scanner, hamlet);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Недопустимый ввод. Выберите действие снова.");
            }
        }
        scanner.close();
    }

    
    
    private static void manageCity(Scanner scanner, City city) {
        boolean managingCity = true;
        while (managingCity) {
            System.out.println("\nВыберите действие для города:");
            System.out.println("1. Показать информацию о городе");
            System.out.println("2. Выбрать нового мэра");
            System.out.println("3. Изменить население");
            System.out.println("4. Изменить бюджет");
            System.out.println("5. Изменить статус города");
            System.out.println("6. Ввести новый закон");
            System.out.println("0. Назад");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    city.showInfo();
                    break;
                case 2:
                    System.out.print("Введите имя нового мэра: ");
                    String newMayor = scanner.nextLine();
                    city.electMayor(newMayor);
                    break;
                case 3:
                    System.out.print("Введите изменение населении  : ");
                    int populationChange = scanner.nextInt();
                    city.modifyPopulation(populationChange);
                    break;
                case 4:
                    System.out.print("Введите новый бюджет: ");
                    double newBudget = scanner.nextDouble();
                    city.editBudget(newBudget);
                    break;
                case 5:
                    System.out.print("Введите статус карантина (true/false): ");
                    boolean quarantineStatus = scanner.nextBoolean();
                    city.changeStatus(quarantineStatus);
                    break;
                case 6:
                    System.out.print("Введите новый закон: ");
                    String law = scanner.nextLine();
                    city.introduceLaw(law);
                    break;
                case 0:
                    managingCity = false;
                    break;
                default:
                    System.out.println("Недопустимый ввод.Выберите действие снова.");
            }
        }
    }
    private static void manageVillage(Scanner scanner, Village village) {
        boolean managingVillage = true;
        while (managingVillage) {
            System.out.println("\nВыберите действие для деревни:");
            System.out.println("1. Показать информацию о деревне");
            System.out.println("2. Выбрать нового мэра");
            System.out.println("3. Изменить население");
            System.out.println("4. Изменить бюджет");
            System.out.println("5. Ввести новый закон");
            System.out.println("0. Назад");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 1:
                    village.showInfo();
                    break;
                case 2:
                    System.out.print("Введите имя нового мэра: ");
                    String newMayor = scanner.nextLine();
                    village.electMayor(newMayor);
                    break;
                case 3:
                    System.out.print("Введите изменение населении  : ");
                    int populationChange = scanner.nextInt();
                    village.modifyPopulation(populationChange);
                    break;
                case 4:
                    System.out.print("Введите новый бюджет: ");
                    double newBudget = scanner.nextDouble();
                    village.editBudget(newBudget);
                    break;
                case 5:
                    System.out.print("Введите новый закон: ");
                    String law = scanner.nextLine();
                    village.introduceLaw(law);
                    break;
                case 0:
                    managingVillage = false;
                    break;
                default:
                    System.out.println("Недопустимый ввод.Выберите действие снова.");
            }
        }
    }

    private static void manageHamlet(Scanner scanner, Hamlet hamlet) {
        boolean managingHamlet = true;
        while (managingHamlet) {
            System.out.println("\nВыберите действие для хутора:");
            System.out.println("1. Показать информацию о хуторе");
            System.out.println("2. Изменить население");
            System.out.println("3. Изменить бюджет");
            System.out.println("4. Ввести новый закон");
            System.out.println("0. Назад");

            int action = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (action) {
                case 1:
                    hamlet.showInfo();
                    break;
                case 2:
                    System.out.print("Введите изменение население  : ");
                    int populationChange = scanner.nextInt();
                    hamlet.modifyPopulation(populationChange);
                    break;
                case 3:
                    System.out.print("Введите новый бюджет: ");
                    double newBudget = scanner.nextDouble();
                    hamlet.editBudget(newBudget);
                    break;
                case 4:
                    System.out.print("Введите новый закон:");
                    String law = scanner.nextLine();
                    hamlet.introduceLaw(law);
                    break;
                case 0:
                    managingHamlet = false;
                    break;
                default:
                    System.out.println("Недопустимый ввод. выберите действие снова.");
            }
        }
    }
}
