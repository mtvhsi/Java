package org.example.chapter_8.v_B_13;
import java.util.*;
import java.util.stream.Collectors;

public class WordSorter {
    public static void main(String[] args) {
        String text = "ааа аа аааааааа ба ввв ббб дд гг га";
        char targetChar = 'а'; /////заданый символ

        List<String> sortedWords = sortWordsByCharacterFrequency(text, targetChar);
        System.out.println(sortedWords);
    }
    public static List<String> sortWordsByCharacterFrequency(String text, char targetChar) {
        String[] words = text.split("\\s+");

        return Arrays.stream(words)
                .filter(word -> !word.isEmpty()) ////исключаем пустые слова
                .sorted(Comparator.comparingInt((String word) ->
                                -countCharOccurrences(word, targetChar))
                        .thenComparing(String::compareTo)) /////по алфавиту
                .collect(Collectors.toList());
    }
    private static int countCharOccurrences(String word, char targetChar) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (c == targetChar) {
                count++;
            }
        }
        return count;
    }
}
