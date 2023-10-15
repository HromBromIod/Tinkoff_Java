package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("11211230, потомок - палиндром")
    void isPalindromeDescendant1() {
        boolean actual = Task5.isPalindromeDescendant(11211230);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("13001120, потомок - палиндром")
    void isPalindromeDescendant2() {
        boolean actual = Task5.isPalindromeDescendant(13001120);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("23336014, потомок - палиндром")
    void isPalindromeDescendant3() {
        boolean actual = Task5.isPalindromeDescendant(23336014);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("11 - палиндром")
    void isPalindromeDescendant4() {
        boolean actual = Task5.isPalindromeDescendant(11);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("12401203, потомоки - не палиндромы")
    void isPalindromeDescendant5() {
        boolean actual = Task5.isPalindromeDescendant(12401203);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("9765, потомоки - не палиндромы")
    void isPalindromeDescendant6() {
        boolean actual = Task5.isPalindromeDescendant(9765);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
