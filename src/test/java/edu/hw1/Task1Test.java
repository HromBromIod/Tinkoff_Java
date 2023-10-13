package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void timeToSecond() {
        int actual = Task1.timeToSecond("::11");
        int expected = -1;
        assertEquals(expected, actual);
        actual = Task1.timeToSecond("01:00");
        expected = 60;
        assertEquals(expected, actual);
        actual = Task1.timeToSecond("02:06");
        expected = 126;
        assertEquals(expected, actual);
        actual = Task1.timeToSecond("02:70");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.timeToSecond("1:00");
        expected = 60;
        assertEquals(expected, actual);
        actual = Task1.timeToSecond("01:1");
        expected = -1;
        assertEquals(expected, actual);
    }
}
