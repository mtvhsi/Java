package org.example.chapter_10.V_B_13.model;

public class GroundCoffee extends Coffee {

    public GroundCoffee(String name, double weight, double price, double volume) {
        super(name, weight, price, volume);
        validate(weight, price);
    }

    private void validate(double weight, double price) {
        if (weight < 0) {
            throw new IllegalArgumentException("Вес не может быть отрицательным");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
    }

    @Override
    public String getType() {
        return "Молотый кофе";
    }
}