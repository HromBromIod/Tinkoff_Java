package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("test1")
    void isPalindromeDescendant1() {
        boolean actual = Task5.isPalindromeDescendant(11211230);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test2")
    void isPalindromeDescendant2() {
        boolean actual = Task5.isPalindromeDescendant(13001120);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test3")
    void isPalindromeDescendant3() {
        boolean actual = Task5.isPalindromeDescendant(23336014);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test4")
    void isPalindromeDescendant4() {
        boolean actual = Task5.isPalindromeDescendant(11);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test5")
    void isPalindromeDescendant5() {
        boolean actual = Task5.isPalindromeDescendant(12401203);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
