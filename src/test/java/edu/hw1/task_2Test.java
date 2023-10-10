package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_2Test {

    @Test
    void countDigits() {
        int actual = task_2.countDigits(100);
        int expected = 3;
        assertEquals(expected, actual);
        actual = task_2.countDigits(-12);
        expected = 2;
        assertEquals(expected, actual);
        actual = task_2.countDigits(-12);
        expected = 2;
        assertEquals(expected, actual);
        actual = task_2.countDigits(1);
        expected = 1;
        assertEquals(expected, actual);
        actual = task_2.countDigits(0);
        expected = 1;
        assertEquals(expected, actual);
    }
}
