package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    @DisplayName("6621 -> 6174 = 5 шагов")
    void countK1() {
        int actual = Task6.countK(6621);
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("6554 -> 6174 = 4 шагов")
    void countK2() {
        int actual = Task6.countK(6554);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1234 -> 6174 = 3 шагов")
    void countK3() {
        int actual = Task6.countK(1234);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2880 -> 6174 = 2 шагов")
    void countK4() {
        int actual = Task6.countK(2880);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("6174 -> 6174 = 0 шагов")
    void countK5() {
        int actual = Task6.countK(6174);
        int expected = 0;
        assertEquals(expected, actual);
    }
}
