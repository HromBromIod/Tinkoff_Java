package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task_3Test {

    @Test
    void isNestable() {
        int[] arrL = new int[] {1, 2, 3, 4};
        int[] arrR = new int[] {0, 6};
        boolean actual = task_3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
        arrL = new int[] {0, 6};
        arrR = new int[] {1, 2};
        actual = task_3.isNestable(arrL, arrR);
        expected = false;
        assertEquals(expected, actual);
        arrL = new int[] {1, 2};
        arrR = new int[] {2, 1};
        actual = task_3.isNestable(arrL, arrR);
        expected = false;
        assertEquals(expected, actual);
        arrL = new int[] {0, 6};
        arrR = new int[] {-1, 7};
        actual = task_3.isNestable(arrL, arrR);
        expected = true;
        assertEquals(expected, actual);
        arrL = new int[] {-1, 7};
        arrR = new int[] {0, 6};
        actual = task_3.isNestable(arrL, arrR);
        expected = false;
        assertEquals(expected, actual);
    }
}
