package org.example.chapter_10.V_C_13;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDirectory {
    public static void main(String[] args) {
        List<String> phoneRecords = new ArrayList<>();
        phoneRecords.add("k89836369887 user1");
        phoneRecords.add("j89526253356 user2");
        phoneRecords.add("a79896552452 user3");
        phoneRecords.add("k86332663266 user4");
        phoneRecords.add("j89995525566 user5");


        File directory = new File("PhoneRecords");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directory, "filtered_phone_records.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (String record : phoneRecords) {
                if (record.startsWith("k") || record.startsWith("j")) {
                    writer.write(record + System.lineSeparator());
                }
            }
            System.out.println("Записи успешно сохранены в файл: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
