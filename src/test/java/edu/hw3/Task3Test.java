package edu.hw3;

import edu.hw3.Task3.MyGenericForFrecDict;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private Task3Test() {
    }

    @Test
    @DisplayName("Integer test")
    void task3IntegerTest() {
        MyGenericForFrecDict<Integer> myGenericClass = new MyGenericForFrecDict<>();
        Map<Integer, Integer> actual =
            myGenericClass.freqDict(Arrays.asList(1, 2, 3, 4, 2, 3, 1, 1, 1));
        Map<Integer, Integer> expected = new HashMap<>() {{
            put(1, 4);
            put(2, 2);
            put(3, 2);
            put(4, 1);
        }};
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("String test")
    void task3StringTest() {
        MyGenericForFrecDict<String> myGenericClass = new MyGenericForFrecDict<>();
        Map<String, Integer> actual =
            myGenericClass.freqDict(Arrays.asList(
                "1",
                "2",
                "3",
                "4",
                "2",
                "3",
                "1",
                "1",
                "1"
            ));
        Map<String, Integer> expected = new HashMap<>() {{
            put("1", 4);
            put("2", 2);
            put("3", 2);
            put("4", 1);
        }};
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Boolean test")
    void task3BooleanTest() {
        MyGenericForFrecDict<Boolean> myGenericClass = new MyGenericForFrecDict<>();
        Map<Boolean, Integer> actual =
            myGenericClass.freqDict(Arrays.asList(true, false, true, true, false, false, true, false, true));
        Map<Boolean, Integer> expected = new HashMap<>() {{
            put(true, 5);
            put(false, 4);
        }};
        assertEquals(actual, expected);
    }
}
