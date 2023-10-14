package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("Кол-во цифр в 100 = 3")
    void countDigits1() {
        int actual = Task2.countDigits(100);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Кол-во цифр в -12 = 2")
    void countDigits2() {
        int actual = Task2.countDigits(-12);
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Кол-во цифр в 1 = 1")
    void countDigits3() {
        int actual = Task2.countDigits(1);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Кол-во цифр в 0 = 1")
    void countDigits4() {
        int actual = Task2.countDigits(0);
        int expected = 1;
        assertEquals(expected, actual);
    }
}
