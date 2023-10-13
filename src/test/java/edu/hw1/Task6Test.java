package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void countK() {
        int actual = Task6.countK(6621);
        int expected = 5;
        assertEquals(expected, actual);
        actual = Task6.countK(6554);
        expected = 4;
        assertEquals(expected, actual);
        actual = Task6.countK(1234);
        expected = 3;
        assertEquals(expected, actual);
        actual = Task6.countK(2880);
        expected = 2;
        assertEquals(expected, actual);
    }
}
