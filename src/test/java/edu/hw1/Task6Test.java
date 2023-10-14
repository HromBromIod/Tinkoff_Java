package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    @DisplayName("test1")
    void countK1() {
        int actual = Task6.countK(6621);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test2")
    void countK2() {
        int actual = Task6.countK(6554);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test3")
    void countK3() {
        int actual = Task6.countK(1234);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test4")
    void countK4() {
        int actual = Task6.countK(2880);
        int expected = 2;
        assertEquals(expected, actual);
    }
}
