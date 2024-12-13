package org.example.chapter_10.V_A_13;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


//// в командную строку нужно ввести input.txt output.txt (обязательно с txt)
public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите путь к входному и выходному файлам");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {
            String line;
            while ((line = reader.readLine()) != null) {
                int maxDigits = getMaxConsecutiveDigits(line);
                writer.write("В строке: \"" + line + "\" наибольшее число подряд идущих цифр: " + maxDigits + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static int getMaxConsecutiveDigits(String line) {
        int max = 0;
        int count = 0;
        for (char c : line.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }
}
