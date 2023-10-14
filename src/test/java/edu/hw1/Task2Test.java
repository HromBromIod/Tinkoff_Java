package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("test1")
    void countDigits1() {
        int actual = Task2.countDigits(100);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test2")
    void countDigits2() {
        int actual = Task2.countDigits(-12);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test3")
    void countDigits3() {
        int actual = Task2.countDigits(-12);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test4")
    void countDigits4() {
        int actual = Task2.countDigits(1);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test5")
    void countDigits5() {
        int actual = Task2.countDigits(0);
        int expected = 1;
        assertEquals(expected, actual);
    }
}
