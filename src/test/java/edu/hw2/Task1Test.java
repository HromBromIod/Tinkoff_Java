package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.params.*;

public class Task1Test {

    @Test
    @DisplayName("Check Constant")
    void checkConstantTest() {
        double actual = new Task1.Expr.Constant(1).evaluate();
        double expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Constant(-1).evaluate();
        expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Constant(0).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Negate")
    void checkNegateTest() {
        double actual = new Task1.Expr.Negate(new Task1.Expr.Constant(1)).evaluate();
        double expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Negate(new Task1.Expr.Constant(-1)).evaluate();
        expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Negate(new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Exponent")
    void checkExponentTest() {
        double actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(1), 2).evaluate();
        double expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(1), 0).evaluate();
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 2).evaluate();
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 3).evaluate();
        expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 0).evaluate();
        expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(0), 0).evaluate();
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(0), 2).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Addition")
    void checkAdditionTest() {
        double actual = new Task1.Expr.Addition(new Task1.Expr.Constant(1), new Task1.Expr.Constant(2)).evaluate();
        double expected = 3;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(-1), new Task1.Expr.Constant(1)).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(0), new Task1.Expr.Constant(0)).evaluate();
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(-2), new Task1.Expr.Constant(1)).evaluate();
        expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(0.5), new Task1.Expr.Constant(0.9)).evaluate();
        expected = 1.4;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check Multiplication")
    void checkMultiplicationTest() {
        double actual =
            new Task1.Expr.Multiplication(new Task1.Expr.Constant(1), new Task1.Expr.Constant(2)).evaluate();
        double expected = 2;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(1), new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(1), new Task1.Expr.Constant(-1)).evaluate();
        expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(-1), new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("MultiCheck")
    void multiCheckTest() {
        var actual = new Task1.Expr.Addition(
            new Task1.Expr.Constant(1),
            new Task1.Expr.Exponent(new Task1.Expr.Multiplication(new Task1.Expr.Addition(
                new Task1.Expr.Constant(2),
                new Task1.Expr.Constant(4)
            ), new Task1.Expr.Negate(new Task1.Expr.Constant(1))), 2)
        );
        double expected = 37;
    }
}
