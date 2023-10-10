package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_6Test {

    @Test
    void countK() {
        int actual = task_6.countK(6621);
        int expected = 5;
        assertEquals(expected, actual);
        actual = task_6.countK(6554);
        expected = 4;
        assertEquals(expected, actual);
        actual = task_6.countK(1234);
        expected = 3;
        assertEquals(expected, actual);
        actual = task_6.countK(2880);
        expected = 2;
        assertEquals(expected, actual);
    }
}
