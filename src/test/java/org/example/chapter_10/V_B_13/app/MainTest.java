package org.example.chapter_10.V_B_13.app;


import org.example.chapter_10.V_B_13.model.Coffee;
import org.example.chapter_10.V_B_13.model.GroundCoffee;
import org.example.chapter_10.V_B_13.model.InstantCoffee;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {

    @Test
    void testGroundCoffee() {
        Coffee groundCoffee = new GroundCoffee("Молотый кофе", 1.5, 500.0, 2.0);
        assertEquals("Молотый кофе", groundCoffee.getName());
        assertEquals(1.5, groundCoffee.getWeight());
        assertEquals(500.0, groundCoffee.getPrice());
        assertEquals(2.0, groundCoffee.getVolume());
        assertEquals("Молотый кофе: 1.50 кг, 500.00 руб., Объем: 2.00 л", groundCoffee.toString());
        assertEquals("Молотый кофе", groundCoffee.getType());
    }

    @Test
    void testInstantCoffee() {
        Coffee instantCoffee = new InstantCoffee("Растворимый кофе", 0.5, 300.0, 1.0);
        assertEquals("Растворимый кофе", instantCoffee.getName());
        assertEquals(0.5, instantCoffee.getWeight());
        assertEquals(300.0, instantCoffee.getPrice());
        assertEquals(1.0, instantCoffee.getVolume());
        assertEquals("Растворимый кофе: 0.50 кг, 300.00 руб., Объем: 1.00 л", instantCoffee.toString());
        assertEquals("Растворимый кофе", instantCoffee.getType());
    }

    @Test
    void testCoffeeSerialization() {
        Coffee originalCoffee = new GroundCoffee("Молотый кофе", 1.0, 400.0, 2.0);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("coffee_test.dat"))) {
            oos.writeObject(originalCoffee);
        } catch (IOException e) {
            fail("Сериализация не удалась: " + e.getMessage());
        }

        Coffee deserializedCoffee = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("coffee_test.dat"))) {
            deserializedCoffee = (Coffee) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            fail("Десериализация не удалась: " + e.getMessage());
        }

        assertNotNull(deserializedCoffee);
        assertEquals(originalCoffee.getName(), deserializedCoffee.getName());
        assertEquals(originalCoffee.getWeight(), deserializedCoffee.getWeight());
        assertEquals(originalCoffee.getPrice(), deserializedCoffee.getPrice());
        assertEquals(originalCoffee.getVolume(), deserializedCoffee.getVolume());
    }
    @Test
    void testInvalidCoffeeCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GroundCoffee("Ошибка кофе", 1.0, -100.0, 1.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new InstantCoffee("Ошибка кофе", -1.0, 300.0, 1.0);
        });
    }

    @Test
    void testToStringWithDifferentValues() {
        Coffee coffee1 = new GroundCoffee("Кофе A", 2.0, 250.0, 1.0);
        assertEquals("Кофе A: 2.00 кг, 250.00 руб., Объем: 1.00 л", coffee1.toString());

        Coffee coffee2 = new InstantCoffee("Кофе B", 1.0, 150.0, 0.5);
        assertEquals("Кофе B: 1.00 кг, 150.00 руб., Объем: 0.50 л", coffee2.toString());
    }
}

