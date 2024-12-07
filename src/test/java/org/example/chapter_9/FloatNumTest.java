package org.example.chapter_9;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FloatNumTest {

    private static final String TEST_FILE_NAME = "test_numbers.txt";

    @Test
    void testParseNumber() throws ParseException {
        assertEquals(1234.56, FloatNum.parseNumber("1234.56", "en"));
        assertEquals(1234.56, FloatNum.parseNumber("1234,56", "de"));
        assertEquals(-7890.12, FloatNum.parseNumber("-7890.12", "en"));
    }

    @Test
    void testParseNumberWithInvalidLocale() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FloatNum.parseNumber("1000.00", "invalid_locale");
        });
        assertEquals("Неизвестная локаль: invalid_locale", exception.getMessage());
    }

    @Test
    void testCalculateSum() {
        List<Double> nums = List.of(1.0, 2.0, 3.0);
        assertEquals(6.0, FloatNum.calculateSum(nums));

        nums = new ArrayList<>();
        assertEquals(0.0, FloatNum.calculateSum(nums));
    }

    private void createTestFile(String content) throws IOException {
        File testFile = new File(TEST_FILE_NAME);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFile))) {
            writer.write(content);
        }
    }

    @Test
    void cleanUp() {
        File testFile = new File(TEST_FILE_NAME);
        if (testFile.exists()) {
            testFile.delete();
        }
    }
}
