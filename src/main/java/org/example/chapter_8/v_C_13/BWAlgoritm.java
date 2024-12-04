package org.example.chapter_8.v_C_13;

import java.util.Arrays;

public class BWAlgoritm{

    public static String burrowsWheelerTransform(String input) {
        int length = input.length();
        String[] rotations = new String[length];
        for (int i = 0; i < length; i++) {
            rotations[i] = input.substring(i) + input.substring(0, i);
        }

        Arrays.sort(rotations);
        StringBuilder result = new StringBuilder();
        for (String rotation : rotations) {
            result.append(rotation.charAt(length - 1));
        }

        return result.toString();
    }
    public static void main(String[] args) {
        String word = "JAVA";
        String transformed = burrowsWheelerTransform(word);
        System.out.println("Преобразование Барроуза-Уиллера слова \"" + word + "\": " + transformed);
    }
}
