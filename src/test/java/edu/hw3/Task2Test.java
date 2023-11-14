package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static edu.hw3.Task2.Clusterizator3000.clusterize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("\"()()()\" -> [\"()\", \"()\", \"()\"]")
    void task2Test1() {
        boolean actual = clusterize("()()()").equals(Arrays.asList("()", "()", "()"));
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"((()))\" -> [\"((()))\"]")
    void task2Test2() {
        boolean actual = clusterize("((()))").equals(List.of("((()))"));
        assertTrue(actual);
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
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"((())())(()(()()))\" -> [\"((())())\", \"(()(()()))\"]")
    void task2Test4() {
        boolean actual =
            clusterize("((())())(()(()()))").equals(Arrays.asList("((())())", "(()(()()))"));
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"()()())\" -> []")
    void task2Test5() {
        boolean actual = clusterize("()()())").equals(List.of());
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"(()()()\" -> []")
    void task2Test6() {
        boolean actual = clusterize("(()()()").equals(List.of());
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"\" -> []")
    void task2Test7() {
        boolean actual = clusterize("").equals(List.of());
        assertTrue(actual);
    }

    @Test
    @DisplayName("\" \" -> []")
    void task2Test8() {
        assertThrows(IllegalArgumentException.class, () -> clusterize(" "));
    }

    @Test
    @DisplayName("\"))((\" -> []")
    void task2Test9() {
        boolean actual = clusterize("))((").equals(List.of());
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"(( ))\" -> []")
    void task2Test10() {
        assertThrows(IllegalArgumentException.class, () -> clusterize("(( ))"));
    }

    @Test
    @DisplayName("\"())(\" -> []")
    void task2Test11() {
        boolean actual = clusterize("())(").equals(List.of());
        assertTrue(actual);
    }

    @Test
    @DisplayName("\"(()))(\" -> []")
    void task2Test12() {
        boolean actual = clusterize("(()))(").equals(List.of());
        assertTrue(actual);
    }
}
