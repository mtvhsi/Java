package org.example.chapter_10.V_B_13.model;

import java.io.Serializable;
import java.util.Locale;

public abstract class Coffee implements Serializable {
    protected String name;
    protected double weight; ///вес в кг
    protected double price; ///цена в рублях
    protected double volume; ///объем в литрах

    private static final long serialVersionUID = 1L;

    public Coffee(String name, double weight, double price, double volume) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.volume = volume;
    }
    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return String.format(Locale.US, "%s: %.2f кг, %.2f руб., Объем: %.2f л",
                name, weight, price, volume);
    }

}
