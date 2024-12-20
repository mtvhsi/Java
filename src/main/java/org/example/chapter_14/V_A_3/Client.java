package org.example.chapter_14.V_A_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class Client {
    private static final String SERVER_ADDRESS = "localhost"; ////фдрес
    private static final int PORT = 7622; ////порт
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            StringBuilder r = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break; //до пустой строчки
                }
                r.append(line).append("\n");
            }
            System.out.println("Получено стихотворение:\n" + r.toString());
        } catch (IOException e) {
            System.err.println("Ошибка при подключении: " + e.getMessage());
        }
    }
}
