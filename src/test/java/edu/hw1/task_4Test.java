package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_4Test {

    @Test
    void fixString() {
        String actual = task_4.fixString("hTsii  s aimex dpus rtni.g");
        String expected = "This is a mixed up string.";
        assertEquals(expected, actual);
        actual = task_4.fixString("123456");
        expected = "214365";
        assertEquals(expected, actual);
        actual = task_4.fixString("badce");
        expected = "abcde";
        assertEquals(expected, actual);
        actual = task_4.fixString("a");
        expected = "a";
        assertEquals(expected, actual);
    }
}
