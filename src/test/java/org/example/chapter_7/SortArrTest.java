package org.example.chapter_7;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class SortArrTest {

    @Test
    public void testSortArrayDescending() {
        Integer[] numbers = {5, 3, 8, 1,-6, 2, 7, 4, 0};
        Integer[] expected = {8, 7, 5, 4, 3, 2, 1, 0, -6};
        SortArr.sortAndDisplay(numbers);
        assertArrayEquals(expected, numbers);
    }

    @Test
    public void testSortArrayAlreadySorted() {
        Integer[] numbers = {8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] expected = {8, 7, 6, 5, 4, 3, 2, 1};

        SortArr.sortAndDisplay(numbers);

        assertArrayEquals(expected, numbers);
    }

    @Test
    public void testSortArrayEmpty() {
        Integer[] numbers = {};
        Integer[] expected = {};

        SortArr.sortAndDisplay(numbers);

        assertArrayEquals(expected, numbers);
    }

    @Test
    public void testSortArraySingleElement() {
        Integer[] numbers = {42};
        Integer[] expected = {42};

        SortArr.sortAndDisplay(numbers);

        assertArrayEquals(expected, numbers);
    }
}
