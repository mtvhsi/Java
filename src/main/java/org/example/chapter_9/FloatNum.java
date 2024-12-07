package org.example.chapter_9;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CustomEx  extends Exception {
    public CustomEx (String n) {
        super(n);
    }
}
public class FloatNum {
    public static void main(String[] args) {
        System.out.println("Разработчик Юматова А.С. Б762-2");
        String fileName = "numbers.txt";

        try {
            List<Double> nums = readFFile(fileName);
            if (nums.isEmpty()) {
                System.out.println("Нет валидных чисел для обработки.");
                return;
            }
            double sum = calculateSum(nums);
            double avg = sum / nums.size();
            System.out.printf("Сумма: %.2f, Среднее: %.2f%n", sum, avg);
        } catch (CustomEx  e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Недостаточно памяти для выполнения программы.");
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }


    protected static List<Double> readFFile(String fileName) throws CustomEx  {
        List<Double> nums = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.trim().split(","); // Разделяем строку на число и локаль
                    if (parts.length != 2) {
                        System.err.println("Некорректный формат записи: " + line);
                        continue;
                    }
                    double n = parseNumber(parts[0].trim(), parts[1].trim());
                    nums.add(n);
                } catch (NumberFormatException | ParseException e) {
                    System.err.println("Некорректная запись в файле: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new CustomEx ("Файл не найден: " + fileName);
        } catch (IOException e) {
            throw new CustomEx  ("Ошибка при чтении файла: " + e.getMessage());
        }

        return nums;
    }


    protected static double parseNumber(String numStr, String localeStr) throws NumberFormatException, ParseException {
        Locale locale;
        switch (localeStr) {
            case "en":
                locale = Locale.US;
                break;
            case "fr":
                locale = Locale.FRANCE;
                break;
            case "de":
                locale = Locale.GERMANY;
                break;
            case "es":
                locale = new Locale("es", "ES");
                break;
            case "it":
                locale = Locale.ITALY;
                break;
            case "ru":
                locale = new Locale("ru", "RU");
                break;
            default:
                throw new IllegalArgumentException("Неизвестная локаль: " + localeStr);
        }
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(numStr).doubleValue();
    }



    protected  static double calculateSum(List<Double> nums) {
        double sum = 0.0;
        for (double n : nums) {
            sum += n;
        }
        return sum;
    }
}
