package org.example.chapter_10.V_B_13.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Truck implements Serializable {
    private List<Coffee> coffeeList;

    private static final long serialVersionUID = 1L;
    public Truck() {
        this.coffeeList = new ArrayList<>();
    }
    public void loadCoffee(Coffee coffee) {
        coffeeList.add(coffee);
    }

    public void sortByPricePerWeight() {
        Collections.sort(coffeeList, Comparator.comparingDouble(c -> c.getPrice() / c.getWeight()));
    }

    public List<Coffee> getCoffeeInPriceRange(double minPrice, double maxPrice) {
        List<Coffee> result = new ArrayList<>();
        for (Coffee coffee : coffeeList) {
            if (coffee.getPrice() >= minPrice && coffee.getPrice() <= maxPrice) {
                result.add(coffee);
            }
        }
        return result;
    }

    public void displayCoffee() {
        coffeeList.forEach(System.out::println);
    }
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении фургона в файл: " + e.getMessage());
        }
    }

    public static Truck loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Truck) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке фургона из файла: " + e.getMessage());
            return new Truck();
        }
    }
}
