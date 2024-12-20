package org.example.chapter_14.V_A_3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    private static final int p = 7622;
    private static final String f = "poems.txt"; ////стихи в файле

    public static void main(String[] args) {
        List<String> ps = load(f);
        if (ps.isEmpty()) {
            System.out.println("Нет стихов для отправки.");
            return;
        }
        try (ServerSocket ss = new ServerSocket(p)) {
            System.out.println("Сервер запущен. Ожидание подключения...");

            while (true) {
                try (Socket c = ss.accept();
                     PrintWriter out = new PrintWriter(c.getOutputStream(), true)) {
                    String r = getRandom(ps);
                    out.println(r);
                    System.out.println("Отправлено стихотворение клиенту: \n" + r);
                } catch (IOException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
    static List<String> load(String f){
        List<String> ps = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            StringBuilder b = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (b.length() > 0) {
                        ps.add(b.toString());
                        b.setLength(0);
                    }
                } else {
                    b.append(line).append("\n");
                }
            }
            if (b.length() > 0) {
                ps.add(b.toString());
            }
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
        return ps;}
    static String getRandom(List<String> ps) {
        if (ps.isEmpty()) {
            throw new IllegalArgumentException("Список стихов пуст.");
        }
        Random r = new Random();
        return ps.get(r.nextInt(ps.size()));
    }
}
