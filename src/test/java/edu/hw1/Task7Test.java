package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    @DisplayName("Отрицательное исходное значение")
    void rotateRight1() {
        int actual = Task7.rotateRight(-1, 1);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Отрицательное исходное значение")
    void rotateRight2() {
        int actual = Task7.rotateRight(-2, 3);
        int expected = -2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("8 -> 2 = 2")
    void rotateRight3() {
        int actual = Task7.rotateRight(8, 2);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("8 -> 1 = 4")
    void rotateRight4() {
        int actual = Task7.rotateRight(8, 1);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("9 -> -2 = 6")
    void rotateRight6() {
        int actual = Task7.rotateRight(9, -2);
        int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("8 -> -1 = 1")
    void rotateRight7() {
        int actual = Task7.rotateRight(8, -1);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Отрицательное исходное значение")
    void rotateLeft1() {
        int actual = Task7.rotateLeft(-1, 1);
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Отрицательное исходное значение")
    void rotateLeft2() {
        int actual = Task7.rotateLeft(-2, 1);
        int expected = -2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 = 1 <- 2")
    void rotateLeft3() {
        int actual = Task7.rotateLeft(1, 2);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3 = 1 <- 5")
    void rotateLeft4() {
        int actual = Task7.rotateLeft(5, 1);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("1 = 1 <- 2")
    void rotateLeft5() {
        int actual = Task7.rotateLeft(2, 1);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("3 = -2 <- 3")
    void rotateLeft6() {
        int actual = Task7.rotateLeft(3, -2);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("6 = -2 <- 9")
    void rotateLeft7() {
        int actual = Task7.rotateLeft(9, -2);
        int expected = 6;
        assertEquals(expected, actual);
    }
}
