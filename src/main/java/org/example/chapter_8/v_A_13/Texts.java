package org.example.chapter_8.v_A_13;
import java.util.HashSet;
import java.util.Set;

public class Texts {
    public static void main(String[] args) {
        String text = "дед мотор ротор кошка радар авто жар";
        printUniqueWords(text);
    }
    public static void printUniqueWords(String text) {
        Set<String> uniqueWords = new HashSet<>();
        String[] words = text.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty() && word.charAt(0) == word.charAt(word.length() - 1)) {
                uniqueWords.add(word);
            }
        }
        if (uniqueWords.isEmpty()) {
            System.out.println("Нет слов, у которых первая и последняя буквы совпадают.");
        } else {
            System.out.println("Уникальные слова, у которых первая и последняя буквы совпадают:");
            for (String uniqueWord : uniqueWords) {
                System.out.println(uniqueWord);
            }
        }
    }
}
