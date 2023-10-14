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
        int expected = -2;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test3")
    void rotateRight3() {
        int actual = Task7.rotateRight(8, 2);
        int expected = 2;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test4")
    void rotateRight4() {
        int actual = Task7.rotateRight(8, 1);
        int expected = 4;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test6")
    void rotateRight6() {
        int actual = Task7.rotateRight(9, -2);
        int expected = 6;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test7")
    void rotateRight7() {
        int actual = Task7.rotateRight(8, -1);
        int expected = 1;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test8")
    void rotateLeft1() {
        int actual = Task7.rotateLeft(-1, 1);
        int expected = -1;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test9")
    void rotateLeft2() {
        int actual = Task7.rotateLeft(-2, 1);
        int expected = -2;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test10")
    void rotateLeft3() {
        int actual = Task7.rotateLeft(1, 2);
        int expected = 1;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test11")
    void rotateLeft4() {
        int actual = Task7.rotateLeft(5, 1);
        int expected = 3;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test12")
    void rotateLeft5() {
        int actual = Task7.rotateLeft(2, 1);
        int expected = 1;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test13")
    void rotateLeft6() {
        int actual = Task7.rotateLeft(3, -2);
        int expected = 3;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("test14")
    void rotateLeft7() {
        int actual = Task7.rotateLeft(9, -2);
        int expected = 6;
        assertEquals(expected, actual);
    }
}
