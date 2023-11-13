package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task6.isSubstring;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    private Task6Test() {
    }

    @Test
    @DisplayName("null string test 1")
    void nullTest1() {
        boolean actual = isSubstring(null, null);
        assertFalse(actual);
    }

    @Test
    @DisplayName("null string test 2")
    void nullTest2() {
        boolean actual = isSubstring(null, "null");
        assertFalse(actual);
    }

    @Test
    @DisplayName("null string test 3")
    void nullTest3() {
        boolean actual = isSubstring("null", null);
        assertFalse(actual);
    }

    @Test
    @DisplayName("is empty string is substring test")
    void emptyTest1() {
        boolean actual = isSubstring("abcdefg", "");
        assertTrue(actual);
    }

    @Test
    @DisplayName("is empty string is substring of empty string test")
    void emptyTest2() {
        boolean actual = isSubstring("", "");
        assertTrue(actual);
    }

    @Test
    @DisplayName("is not empty string is substring test")
    void substringTest() {
        boolean actual = isSubstring("abcdefg", "cde");
        assertTrue(actual);
    }

    @Test
    @DisplayName("is string is not substring test")
    void notSubstringTest() {
        boolean actual = isSubstring("abc", "def");
        assertFalse(actual);
    }
}
