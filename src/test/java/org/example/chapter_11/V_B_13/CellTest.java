package org.example.chapter_11.V_B_13;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;

class CircleCellsTest {

    @Test
    void testIsCellInsideCircle_TrueForInsideCell() {
        Cell cell = new Cell(4, 4);
        assertTrue(CircleCells.isCellInsideCircle(cell, 5, 5, 4));
    }

    @Test
    void testIsCellInsideCircle_FalseForOutsideCell() {
        Cell cell = new Cell(8, 8);
        assertFalse(CircleCells.isCellInsideCircle(cell, 5, 5, 4));
    }

    @Test
    void testIsCellInsideCircle_FalseForEdgeCell() {
        Cell cell = new Cell(5, 8);
        assertFalse(CircleCells.isCellInsideCircle(cell, 5, 5, 4));
    }

    @Test
    void testDistanceToCenter() {
        Cell cell = new Cell(4, 4);
        double distance = cell.distanceToCenter(5, 5);
        assertEquals(Math.sqrt(2), distance, 0.001);
    }

    @Test
    void testPriorityQueueOrder() {
        PriorityQueue<Cell> queue = new PriorityQueue<>(
                (cell1, cell2) -> Double.compare(cell1.distanceToCenter(5, 5),
                        cell2.distanceToCenter(5, 5))
        );

        queue.offer(new Cell(4, 4));
        queue.offer(new Cell(5, 5));
        queue.offer(new Cell(3, 5));

        assertEquals(new Cell(5, 5).toString(), queue.poll().toString());
        assertEquals(new Cell(4, 4).toString(), queue.poll().toString());
        assertEquals(new Cell(3, 5).toString(), queue.poll().toString());
    }
}
