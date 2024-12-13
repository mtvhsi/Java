package org.example.chapter_10.V_A_13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMaxConsecutiveDigits_SingleDigit() {
        assertEquals(1, Main.getMaxConsecutiveDigits("a1b"));
    }

    @Test
    void testMaxConsecutiveDigits_ConsecutiveDigits() {
        assertEquals(4, Main.getMaxConsecutiveDigits("aa123bb4567"));
    }

    @Test
    void testMaxConsecutiveDigits_NoDigits() {
        assertEquals(0, Main.getMaxConsecutiveDigits("abcdef"));
    }

    @Test
    void testMaxConsecutiveDigits_EndWithDigits() {
        assertEquals(2, Main.getMaxConsecutiveDigits("abc12"));
    }

    @Test
    void testMaxConsecutiveDigits_OnlyDigits() {
        assertEquals(5, Main.getMaxConsecutiveDigits("12345"));
    }

    @Test
    void testMaxConsecutiveDigits_SpacesAndSymbols() {
        assertEquals(4, Main.getMaxConsecutiveDigits("!@# 12 $$ 3456"));
    }

    @Test
    void testMaxConsecutiveDigits_Mixed() {
        assertEquals(5, Main.getMaxConsecutiveDigits("12abc3456d78923efg"));
    }
}
