package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw5.Task7.STRING_PATTERN_1;
import static edu.hw5.Task7.STRING_PATTERN_2;
import static edu.hw5.Task7.STRING_PATTERN_3;
import static edu.hw5.Task7.isCorrectString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task7Test {
    private Task7Test() {
    }

    @Test
    @DisplayName("null test")
    void nullTest() {
        boolean actual = isCorrectString(null, null);
        assertFalse(actual);
    }

    @Test
    @DisplayName("empty string test 1")
    void emptyTest1() {
        boolean actual = isCorrectString("", STRING_PATTERN_1);
        assertFalse(actual);
    }

    @Test
    @DisplayName("empty string test 2")
    void emptyTest2() {
        boolean actual = isCorrectString("", STRING_PATTERN_2);
        assertFalse(actual);
    }

    @Test
    @DisplayName("empty string test 3")
    void emptyTest3() {
        boolean actual = isCorrectString("", STRING_PATTERN_3);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 1 successful test")
    void stringPattern1SuccessfulTest() {
        boolean actual = isCorrectString("1101", STRING_PATTERN_1);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 1 fail test 1")
    void stringPattern1FailTest1() {
        boolean actual = isCorrectString("11", STRING_PATTERN_1);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 1 fail test 2")
    void stringPattern1FailTest2() {
        boolean actual = isCorrectString("111", STRING_PATTERN_1);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 1 fail test 3")
    void stringPattern1FailTest3() {
        boolean actual = isCorrectString("1102", STRING_PATTERN_1);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 1 fail test 4")
    void stringPattern1FailTest4() {
        boolean actual = isCorrectString("210", STRING_PATTERN_1);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 2 successful test 1")
    void stringPattern2SuccessfulTest1() {
        boolean actual = isCorrectString("1001", STRING_PATTERN_2);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 2 successful test 2")
    void stringPattern2SuccessfulTest2() {
        boolean actual = isCorrectString("000", STRING_PATTERN_2);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 2 successful test 3")
    void stringPattern2SuccessfulTest3() {
        boolean actual = isCorrectString("1", STRING_PATTERN_2);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 2 successful test 4")
    void stringPattern2SuccessfulTest4() {
        boolean actual = isCorrectString("0", STRING_PATTERN_2);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 2 fail test 1")
    void stringPattern2FailTest1() {
        boolean actual = isCorrectString("1000", STRING_PATTERN_2);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 2 fail test 2")
    void stringPattern2FailTest2() {
        boolean actual = isCorrectString("001", STRING_PATTERN_2);
        assertFalse(actual);
    }

    @Test
    @DisplayName("string pattern 3 successful test 1")
    void stringPattern3SuccessfulTest1() {
        boolean actual = isCorrectString("1", STRING_PATTERN_3);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 3 successful test 2")
    void stringPattern3SuccessfulTest2() {
        boolean actual = isCorrectString("10", STRING_PATTERN_3);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 3 successful test 3")
    void stringPattern3SuccessfulTest3() {
        boolean actual = isCorrectString("101", STRING_PATTERN_3);
        assertTrue(actual);
    }

    @Test
    @DisplayName("string pattern 3 fail test")
    void stringPattern3FailTest() {
        boolean actual = isCorrectString("1001", STRING_PATTERN_3);
        assertFalse(actual);
    }
}
