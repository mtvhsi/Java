package org.example.chapter_8.v_B_13;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordSorterTest {

    @Test
    public void testBasicFunctionality() {
        String text = "ааа аа аааааааа ба ввв ббб дд гг га";
        char targetChar = 'г';
        List<String> expected = List.of("гг", "га", "аа", "ааа", "аааааааа", "ба", "ббб", "ввв", "дд");

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }


    @Test
    public void testNoOccurrences() {
        String text = "ааа ддд ггг ббб";
        char targetChar = 'и';
        List<String> expected = List.of("ааа", "ббб", "ггг", "ддд");

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }

    @Test
    public void testEqualFrequencySorting() {
        String text = "разных слов с синами ссис";
        char targetChar = 'с';
        List<String> expected = List.of("ссис", "с", "синами", "слов", "разных");

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyText() {
        String text = "";
        char targetChar = 'с';
        List<String> expected = List.of();

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }

    @Test
    public void testSingleWord() {
        String text = "солнце";
        char targetChar = 'с';
        List<String> expected = List.of("солнце");

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }

    @Test
    public void testWordsWithDifferentCase() {
        String text = "Солнце солнце СОЛНЦЕ";
        char targetChar = 'С';
        List<String> expected = List.of("СОЛНЦЕ", "Солнце", "солнце");

        List<String> result = WordSorter.sortWordsByCharacterFrequency(text, targetChar);
        assertEquals(expected, result);
    }
}
