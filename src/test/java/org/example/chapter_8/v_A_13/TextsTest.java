package org.example.chapter_8;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextsTest {

    @Test
    public void testUniqueWordsWithMatchingFirstLastLetters() {
        String text = "дед мотор ротор кошка радар авто жар";
        String expectedOutput = "Уникальные слова, у которых первая и последняя буквы совпадают:\n" +
                "дед\n" +
                "мотор\n" +
                "ротор\n" +
                "радар\n";

        System.out.println("Фактический вывод: '" + text.toString().trim() + "'");
        System.out.println("Ожидаемый вывод: '" + expectedOutput.trim() + "'");

    }

    @Test
    public void testNoMatchingWords() {
        String text = "кошка собака";
        String expectedOutput = "Нет слов, у которых первая и последняя буквы совпадают.\n";

        assertConsoleOutput(text, expectedOutput);
    }

    @Test
    public void testEmptyString() {
        String text = "";
        String expectedOutput = "Нет слов, у которых первая и последняя буквы совпадают.\n";

        assertConsoleOutput(text, expectedOutput);
    }

    @Test
    public void testAllMatchingWords() {
        String text = "мама команда мама";
        String expectedOutput = "Уникальные слова, у которых первая и последняя буквы совпадают:\n" +
                "мама\n" +
                "команда\n";

        System.out.println("Фактический вывод: '" + text.toString().trim() + "'");
        System.out.println("Ожидаемый вывод: '" + expectedOutput.trim() + "'");
    }

    private void assertConsoleOutput(String input, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Texts.printUniqueWords(input);
        System.setOut(System.out);

        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
