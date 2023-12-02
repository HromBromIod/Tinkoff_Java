package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task4.isCorrectPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private Task4Test() {
    }

    @Test
    @DisplayName("null test")
    void nullTest() {
        boolean actual = isCorrectPassword(null);
        assertFalse(actual);
    }

    @Test
    @DisplayName("empty test")
    void emptyTest() {
        boolean actual = isCorrectPassword("");
        assertFalse(actual);
    }

    @Test
    @DisplayName("correct password test 1")
    void correctPasswordTest1() {
        boolean actual = isCorrectPassword("abc~");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 2")
    void correctPasswordTest2() {
        boolean actual = isCorrectPassword("ab!c");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 3")
    void correctPasswordTest3() {
        boolean actual = isCorrectPassword("a@bc");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 4")
    void correctPasswordTest4() {
        boolean actual = isCorrectPassword("#abc");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 5")
    void correctPasswordTest5() {
        boolean actual = isCorrectPassword("a$bc");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 6")
    void correctPasswordTest6() {
        boolean actual = isCorrectPassword("ab%c");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 7")
    void correctPasswordTest7() {
        boolean actual = isCorrectPassword("abc^");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 8")
    void correctPasswordTest8() {
        boolean actual = isCorrectPassword("ab&c");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 9")
    void correctPasswordTest9() {
        boolean actual = isCorrectPassword("a*bc");
        assertTrue(actual);
    }

    @Test
    @DisplayName("correct password test 10")
    void correctPasswordTest10() {
        boolean actual = isCorrectPassword("|abc");
        assertTrue(actual);
    }

    @Test
    @DisplayName("incorrect password test 11")
    void correctPasswordTest11() {
        boolean actual = isCorrectPassword("~!@#$%^&*|");
        assertFalse(actual);
    }
}
