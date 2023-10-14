package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    @DisplayName("test1")
    void timeToSecond1() {
        int actual = Task1.timeToSecond("::11");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test2")
    void timeToSecond2() {
        int actual = Task1.timeToSecond("01:00");
        int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test3")
    void timeToSecond3() {
        int actual = Task1.timeToSecond("02:06");
        int expected = 126;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test4")
    void timeToSecond4() {
        int actual = Task1.timeToSecond("02:70");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test5")
    void timeToSecond5() {
        int actual = Task1.timeToSecond("1:00");
        int expected = 60;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test6")
    void timeToSecond6() {
        int actual = Task1.timeToSecond("01:1");
        int expected = -1;
        assertEquals(expected, actual);
    }
}
