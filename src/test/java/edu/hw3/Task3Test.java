package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static edu.hw3.Task3.Task3.freqDict;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private Task3Test() {
    }

    @Test
    @DisplayName("Integer test")
    void task3IntegerTest() {
        boolean actual =
            freqDict(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 2, 3, 1, 1, 1))).equals(new HashMap<>() {{
                put(1, 4);
                put(2, 2);
                put(3, 2);
                put(4, 1);
            }});
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("String test")
    void task3StringTest() {
        boolean actual =
            freqDict(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "2", "3", "1", "1", "1"))).equals(new HashMap<>() {{
                put("1", 4);
                put("2", 2);
                put("3", 2);
                put("4", 1);
            }});
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Boolean test")
    void task3BooleanTest() {
        boolean actual =
            freqDict(new ArrayList<>(Arrays.asList(true, false, true, true, false, false, true, false, true))).equals(new HashMap<>() {{
                put(true, 5);
                put(false, 4);
            }});
        boolean expected = true;
        assertEquals(actual, expected);
    }
}
