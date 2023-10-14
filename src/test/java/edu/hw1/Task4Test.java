package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    @DisplayName("hTsii  s aimex dpus rtni.g -> This is a mixed up string.")
    void fixString1() {
        String actual = Task4.fixString("hTsii  s aimex dpus rtni.g");
        String expected = "This is a mixed up string.";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("123456 -> 214365")
    void fixString2() {
        String actual = Task4.fixString("123456");
        String expected = "214365";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("badce -> abcde")
    void fixString3() {
        String actual = Task4.fixString("badce");
        String expected = "abcde";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("a -> a")
    void fixString4() {
        String actual = Task4.fixString("a");
        String expected = "a";
        assertEquals(expected, actual);
    }
}
