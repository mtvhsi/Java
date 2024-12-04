package org.example.chapter_7;

import java.util.Arrays;

public class SortArr {

    public static void sortAndDisplay(Integer[] numbers) {
        Arrays.sort(numbers, (a, b) -> b.compareTo(a));
        System.out.println("Отсортированный массив по убыванию: " + Arrays.toString(numbers));
    }

    public static void main(String[] args) {
        Integer[] numbers = {5, 3, 8, 1, 2, 7, 4, 6};
        sortAndDisplay(numbers);
    }
}
