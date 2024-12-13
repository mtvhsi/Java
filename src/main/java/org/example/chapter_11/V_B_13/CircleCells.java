package org.example.chapter_11.V_B_13;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Cell {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double distanceToCenter(int centerX, int centerY) {
        return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}

public class CircleCells {
    public static void main(String[] args) {
        int centerX = 5;
        int centerY = 5;
        int radius = 4;

        PriorityQueue<Cell> cellsInsideCircle = new PriorityQueue<>(
                (cell1, cell2) -> Double.compare(cell1.distanceToCenter(centerX, centerY),
                        cell2.distanceToCenter(centerX, centerY))
        );

        IntStream.range(0, 10).forEach(x -> {
            IntStream.range(0, 10).forEach(y -> {
                Cell cell = new Cell(x, y);
                if (isCellInsideCircle(cell, centerX, centerY, radius)) {
                    cellsInsideCircle.offer(cell);
                }
            });
        });

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cells_inside_circle.txt"))) {
            while (!cellsInsideCircle.isEmpty()) {
                writer.write(cellsInsideCircle.poll().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static boolean isCellInsideCircle(Cell cell, int centerX, int centerY, int radius) {
        return Math.pow(cell.x - centerX + 0.5, 2) + Math.pow(cell.y - centerY + 0.5, 2) < Math.pow(radius, 2) &&
                Math.pow(cell.x + 0.5 - centerX + 0.5, 2) + Math.pow(cell.y - centerY + 0.5, 2) < Math.pow(radius, 2) &&
                Math.pow(cell.x - centerX + 0.5, 2) + Math.pow(cell.y + 0.5 - centerY + 0.5, 2) < Math.pow(radius, 2) &&
                Math.pow(cell.x + 0.5 - centerX + 0.5, 2) + Math.pow(cell.y + 0.5 - centerY + 0.5, 2) < Math.pow(radius, 2);
    }

}
