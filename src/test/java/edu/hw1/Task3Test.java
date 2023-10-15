package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    @DisplayName("Массив {1,2,3,4} входит в интервал {0,6}")
    void isNestable1() {
        int[] arrL = new int[] {1, 2, 3, 4};
        int[] arrR = new int[] {0, 6};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Массив {0, 6} не входит в интервал {1, 2}")
    void isNestable2() {
        int[] arrL = new int[] {0, 6};
        int[] arrR = new int[] {1, 2};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Массив {1,24} не входит в интервал {2, 1}")
    void isNestable3() {
        int[] arrL = new int[] {1, 2};
        int[] arrR = new int[] {2, 1};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Массив {0, 6} входит в интервал {-1, 7}")
    void isNestable4() {
        int[] arrL = new int[] {0, 6};
        int[] arrR = new int[] {-1, 7};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Массив {-1, 7} не входит в интервал {0,6}")
    void isNestable5() {
        int[] arrL = new int[] {-1, 7};
        int[] arrR = new int[] {0, 6};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Массив {-1, 5, 8, 7} входит в интервал {9, -2}")
    void isNestable6() {
        int[] arrL = new int[] {-1, 5, 8, 7};
        int[] arrR = new int[] {9, -2};
        boolean actual = Task3.isNestable(arrL, arrR);
        boolean expected = true;
        assertEquals(expected, actual);
    }
}
