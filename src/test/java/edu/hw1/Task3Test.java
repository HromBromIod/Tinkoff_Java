package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    @DisplayName("test1")
    void isNestable1() {
        int[] arrL = new int[] {1, 2, 3, 4};
        int[] arrR = new int[] {0, 6};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test2")
    void isNestable2() {
        int[] arrL = new int[] {0, 6};
        int[] arrR = new int[] {1, 2};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test3")
    void isNestable3() {
        int[] arrL = new int[] {1, 2};
        int[] arrR = new int[] {2, 1};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test4")
    void isNestable4() {
        int[] arrL = new int[] {0, 6};
        int[] arrR = new int[] {-1, 7};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("test5")
    void isNestable5() {
        int[] arrL = new int[] {-1, 7};
        int[] arrR = new int[] {0, 6};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
