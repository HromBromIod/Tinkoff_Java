package edu.hw2.task1;

public record Negate(Expr value) implements Expr {
    public double evaluate() {
        if (value.evaluate() != 0) {
            return (-1) * value.evaluate();
        } else {
            return value.evaluate();
        }
    }
}
