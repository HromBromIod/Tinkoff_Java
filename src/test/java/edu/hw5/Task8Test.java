package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw5.Task8.STRING_PATTERN_1;
import static edu.hw5.Task8.STRING_PATTERN_2;
import static edu.hw5.Task8.STRING_PATTERN_5;
import static edu.hw5.Task8.STRING_PATTERN_7;
import static edu.hw5.Task8.isValidString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {
    private Task8Test() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"10011", "1101111", "110"})
    @DisplayName("valid string of string pattern 1")
    void validStringPattern1(String strings) {
        assertTrue(isValidString(strings, STRING_PATTERN_1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1011", "23", "", " "})
    @DisplayName("invalid string of string pattern 1")
    void invalidStringPattern1(String strings) {
        assertFalse(isValidString(strings, STRING_PATTERN_1));
    }

    @Test
    @DisplayName("null string of string pattern 1")
    void invalidStringPattern1Null() {
        assertFalse(isValidString(null, STRING_PATTERN_1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100011", "011", "1101", "000"})
    @DisplayName("valid string of string pattern 2")
    void validStringPattern2(String strings) {
        assertTrue(isValidString(strings, STRING_PATTERN_2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101", "0101", "23", "", " "})
    @DisplayName("invalid string of string pattern 2")
    void invalidStringPattern2(String strings) {
        assertFalse(isValidString(strings, STRING_PATTERN_2));
    }

    @Test
    @DisplayName("null string of string pattern 2")
    void invalidStringPattern2Null() {
        assertFalse(isValidString(null, STRING_PATTERN_2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010111", "101", "1111111"})
    @DisplayName("valid string of string pattern 3")
    void validStringPattern3(String strings) {
        assertTrue(isValidString(strings, STRING_PATTERN_5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001", "01", "23", "", " "})
    @DisplayName("invalid string of string pattern 3")
    void invalidStringPattern3(String strings) {
        assertFalse(isValidString(strings, STRING_PATTERN_5));
    }

    @Test
    @DisplayName("null string of string pattern 3")
    void invalidStringPattern3Null() {
        assertFalse(isValidString(null, STRING_PATTERN_5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010101", "001", "1001001"})
    @DisplayName("valid string of string pattern 4")
    void validStringPattern4(String strings) {
        assertTrue(isValidString(strings, STRING_PATTERN_7));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1101", "011", "11111111011111111", "23", "", " "})
    @DisplayName("invalid string of string pattern 4")
    void invalidStringPattern4(String strings) {
        assertFalse(isValidString(strings, STRING_PATTERN_7));
    }

    @Test
    @DisplayName("null string of string pattern 4")
    void invalidStringPattern4Null() {
        assertFalse(isValidString(null, STRING_PATTERN_7));
    }
}
