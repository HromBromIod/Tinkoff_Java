package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    @DisplayName("test1")
    void rotateRight1() {
        int actual = Task7.rotateRight(-1, 1);
        int expected = -1;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test2")
    void rotateRight2() {
        int actual = Task7.rotateRight(-2, 3);
        int expected = -1;
        assertEquals(expected, actual);
    }
}
