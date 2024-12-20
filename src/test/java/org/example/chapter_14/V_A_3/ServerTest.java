package org.example.chapter_14.V_A_3;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {

    private static final String TEST_FILE_PATH = "poems.txt";
    @Test
    void testLoad_withValidFile() {
        List<String> poems = Server.load(TEST_FILE_PATH);
        assertFalse(poems.isEmpty());
        assertEquals(6, poems.size());
        assertTrue(poems.get(0).contains("Моя душа, как ястреб дикий"));
    }

    @Test
    void testLoad_withEmptyFile() {
        List<String> poems = Server.load("empty.txt");
        assertTrue(poems.isEmpty());
    }
    @Test
    void testGetRandom_withValidList() {
        List<String> poems = Server.load(TEST_FILE_PATH);
        String randomPoem = Server.getRandom(poems);
        assertTrue(poems.contains(randomPoem));
    }

    @Test
    void testGetRandom_fromEmptyList() {
        List<String> emptyList = List.of();
        assertThrows(IllegalArgumentException.class, () -> Server.getRandom(emptyList));
    }
}

