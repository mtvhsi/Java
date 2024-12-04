package org.example.chapter_8;
import java.util.HashSet;

public class Texts {
    public static void main(String[] args) {
        String text = "дед мотор ротор кошка радар авто жар";
        printUniqueWords(text);
    }

    public static void printUniqueWords(String text) {
        HashSet<String> uniqueWords = new HashSet<>();
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
