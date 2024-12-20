package org.example.chapter_14.V_B_1;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 7622;

    public static void main(String[] args) {
        new Client().start();
    }
    private void start() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
                if (response.contains("Ваш ход!")) {
                    String coordinates = console.readLine();
                    out.println(coordinates);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}