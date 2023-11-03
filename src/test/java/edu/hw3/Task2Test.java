package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static edu.hw3.Task2.Clusterizator3000.clusterize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("\"()()()\" -> [\"()\", \"()\", \"()\"]")
    void task2Test1() {
        boolean actual = clusterize("()()()").equals(Arrays.asList("()", "()", "()"));
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"((()))\" -> [\"((()))\"]")
    void task2Test2() {
        boolean actual = clusterize("((()))").equals(List.of("((()))"));
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"((()))(())()()(()())\" -> [\"((()))\", \"(())\", \"()\", \"()\", \"(()())\"]")
    void task2Test3() {
        boolean actual = clusterize("((()))(())()()(()())").equals(Arrays.asList(
            "((()))",
            "(())",
            "()",
            "()",
            "(()())"
        ));
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"((())())(()(()()))\" -> [\"((())())\", \"(()(()()))\"]")
    void task2Test4() {
        boolean actual =
            clusterize("((())())(()(()()))").equals(Arrays.asList("((())())", "(()(()()))"));
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"()()())\" -> [\"()\", \"()\", \"()\"]")
    void task2Test5() {
        boolean actual = clusterize("()()())").equals(Arrays.asList("()", "()", "()"));
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"(()()()\" -> []")
    void task2Test6() {
        boolean actual = clusterize("(()()()").equals(List.of());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"\" -> []")
    void task2Test7() {
        boolean actual = clusterize("").equals(List.of());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\" \" -> []")
    void task2Test8() {
        boolean actual = clusterize(" ").equals(List.of());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"))((\" -> []")
    void task2Test9() {
        boolean actual = clusterize("))((").equals(List.of());
        boolean expected = true;
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("\"(( ))\" -> []")
    void task2Test10() {
        boolean actual = clusterize("(( ))").equals(List.of());
        boolean expected = true;
        assertEquals(actual, expected);
    }
}
