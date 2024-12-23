package org.example.chapter_10.V_C_13;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneDirectoryTest {

    private File directory;
    private File file;

    @BeforeEach
    public void setUp() {
        directory = new File("PhoneRecords");
        if (!directory.exists()) {
            directory.mkdir();
        }
        file = new File(directory, "phone.txt");
    }

    @AfterEach
    public void tearDown() {
        if (file.exists()) {
            file.delete();
        }
        if (directory.exists()) {
            directory.delete();
        }
    }
    @Test
    public void testMetodsPhone() throws IOException {
        List<String> phoneRecords = new ArrayList<>();
        phoneRecords.add("123456789 user");
        phoneRecords.add("j987654321 user");
        phoneRecords.add("kkk123456789 user");
        phoneRecords.add("kkkk555567890 user");
        phoneRecords.add("kj222333444 user");
        PhoneDirectory.metodsPhone(phoneRecords);
        assertTrue(file.exists());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> recordsFromFile = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                recordsFromFile.add(line);
            }
            assertTrue(recordsFromFile.contains("j987654321 user"));
            assertTrue(recordsFromFile.contains("kkk123456789 user"));
            assertTrue(recordsFromFile.contains("kj222333444 user"));
            assertFalse(recordsFromFile.contains("123456789 user"));
            assertTrue(recordsFromFile.contains("kkkk555567890 user"));
        }
    }
}