package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_5Test {

    @Test
    void isPalindromeDescendant() {
        boolean actual = task_5.isPalindromeDescendant(11211230);
        boolean expected = true;
        assertEquals(expected, actual);
        actual = task_5.isPalindromeDescendant(13001120);
        expected = true;
        assertEquals(expected, actual);
        actual = task_5.isPalindromeDescendant(23336014);
        expected = true;
        assertEquals(expected, actual);
        actual = task_5.isPalindromeDescendant(11);
        expected = true;
        assertEquals(expected, actual);
        actual = task_5.isPalindromeDescendant(12401203);
        expected = false;
        assertEquals(expected, actual);
    }
}
