package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static edu.hw5.Task2.getFridayThirteenDats;
import static edu.hw5.Task2.nextFridayThirteen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("1925 friday thirteen  test")
    void year1925Test() {
        List<LocalDate> actual = getFridayThirteenDats(1925);
        List<LocalDate> expected =
            List.of(LocalDate.parse("1925-02-13"), LocalDate.parse("1925-03-13"), LocalDate.parse("1925-11-13"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2024 friday thirteen  test")
    void year2024Test() {
        List<LocalDate> actual = getFridayThirteenDats(2024);
        List<LocalDate> expected =
            List.of(LocalDate.parse("2024-09-13"), LocalDate.parse("2024-12-13"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("null next friday thirteen test")
    void nextFridayThirteenNullTest() {
        assertThrows(IllegalArgumentException.class, () -> nextFridayThirteen(null));
    }

    @Test
    @DisplayName("2024-09-12 next friday thirteen test")
    void nextFridayThirteenTest1() {
        LocalDate actual = nextFridayThirteen(LocalDate.parse("2024-09-12"));
        LocalDate expected = LocalDate.parse("2024-09-13");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("2024-09-13 next friday thirteen test")
    void nextFridayThirteenTest2() {
        LocalDate actual = nextFridayThirteen(LocalDate.parse("2024-09-13"));
        LocalDate expected = LocalDate.parse("2024-12-13");
        assertEquals(expected, actual);
    }
}
