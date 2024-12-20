package org.example.chapter_14.V_B_1;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 7622;
    private static final char SEA = '~';
    private static final char BOAT = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private char[][] board;
    private boolean pl1Turn = true;
    private boolean active = true;

    public Server() {
        board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(board[i], SEA);
        }
        board[1][1] = BOAT;
        board[2][2] = BOAT;
    }

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        System.out.println("Сервер активен...");
        try (ServerSocket srvSocket = new ServerSocket(PORT)) {
            Socket p1Socket = srvSocket.accept();
            System.out.println("Игрок 1 подключён.");
            Socket p2Socket = srvSocket.accept();
            System.out.println("Игрок 2 подключён.");

            new PHandler(p1Socket, "Игрок 1").start();
            new PHandler(p2Socket, "Игрок 2").start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized String currentPlayer() {
        return pl1Turn ? "Игрок 1" : "Игрок 2";
    }

    private synchronized void switchPlayer() {
        pl1Turn = !pl1Turn;
        notifyAll();
    }

    private class PHandler extends Thread {
        private Socket socket;
        private String name;
        private PrintWriter out;
        private BufferedReader in;

        public PHandler(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Добро пожаловать, " + name + "!");

                while (active) {
                    synchronized (Server.this) {
                        while (!name.equals(currentPlayer())) {
                            try {
                                Server.this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    out.println("Ваш ход! Введите координаты (например, 1 2):");
                    String input = in.readLine();
                    if (input == null) {
                        break;
                    }
                    String[] parts = input.split(" ");
                    int n = Integer.parseInt(parts[0]);
                    int m = Integer.parseInt(parts[1]);

                    char result = makeMove(n, m);
                    out.println("Результат: " + result);
                    notifyPlayers();

                    if (result == HIT) {
                        out.println("Попадание!");
                    } else if (result == MISS) {
                        out.println("Промах!");
                    }

                    if (isFinished()) {
                        out.println("Игра окончена! " + name + " выиграл!");
                        active = false;
                        notifyPlayers();
                        break;
                    }
                    switchPlayer();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private char makeMove(int n, int m) {
            if (n < 0 || n >= 5 || m < 0 || m >= 5) {
                return 'E';
            }
            char cell = board[n][m];
            if (cell == SEA) {
                board[n][m] = MISS;
                return MISS;
            } else if (cell == BOAT) {
                board[n][m] = HIT;
                return HIT;
            }
            return 'E';
        }

        private void notifyPlayers() {
            for (Thread t : Thread.getAllStackTraces().keySet()) {
                if (t instanceof PHandler) {
                    ((PHandler) t).out.println("Поля: " + Arrays.deepToString(board));
                }
            }
        }

        private boolean isFinished() {
            for (char[] row : board) {
                for (char cell : row) {
                    if (cell == BOAT) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
