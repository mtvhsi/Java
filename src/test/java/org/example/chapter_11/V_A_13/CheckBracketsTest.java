package org.example.chapter_11.V_A_13;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckBracketsTest {

    @Test
    public void testValidBrackets() {
        assertTrue(CheckBrackets.check("{[()]}"));
        assertTrue(CheckBrackets.check("([])"));
        assertTrue(CheckBrackets.check("{[()]}{[()]()}"));
        assertTrue(CheckBrackets.check("{{[[(())]]}}"));
    }

    @Test
    public void testInvalidBrackets() {
        assertFalse(CheckBrackets.check("{[(])}"));
        assertFalse(CheckBrackets.check("{[}"));
        assertFalse(CheckBrackets.check("("));
        assertFalse(CheckBrackets.check("]"));
        assertFalse(CheckBrackets.check("{[()]}}"));
    }

    @Test
    public void testEmptyString() {
        assertTrue(CheckBrackets.check(""));
    }
}
