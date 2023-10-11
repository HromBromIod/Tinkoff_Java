package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_7Test {

    @Test
    void rotateRight() {
        int actual = task_7.rotateRight(-1, 1);
        int expected = -1;
        assertEquals(expected, actual);
    }
}
