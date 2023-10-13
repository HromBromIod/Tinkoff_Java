package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void fixString() {
        String actual = Task4.fixString("hTsii  s aimex dpus rtni.g");
        String expected = "This is a mixed up string.";
        assertEquals(expected, actual);
        actual = Task4.fixString("123456");
        expected = "214365";
        assertEquals(expected, actual);
        actual = Task4.fixString("badce");
        expected = "abcde";
        assertEquals(expected, actual);
        actual = Task4.fixString("a");
        expected = "a";
        assertEquals(expected, actual);
    }
}
