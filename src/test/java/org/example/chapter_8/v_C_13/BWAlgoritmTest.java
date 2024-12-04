package org.example.chapter_8.v_C_13;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BWAlgoritmTest {

    @Test
    public void testBasicWord() {
        String input = "JAVA";
        String expected = "VJAA";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyString() {
        String input = "";
        String expected = "";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testSingleCharacter() {
        String input = "A";
        String expected = "A";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testRepeatingCharacters() {
        String input = "AAAA";
        String expected = "AAAA";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testDifferentCharacters() {
        String input = "BANANA";
        String expected = "NNBAAA";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }

    @Test
    public void testAlphabeticalOrder() {
        String input = "ABC";
        String expected = "CAB";
        String result = BWAlgoritm.burrowsWheelerTransform(input);
        assertEquals(expected, result);
    }
}
