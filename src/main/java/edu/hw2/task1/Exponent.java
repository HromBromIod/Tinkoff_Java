package edu.hw2.task1;

public record Exponent(Expr value, int degree) implements Expr {
    public double evaluate() {
        return Math.pow(value.evaluate(), degree);
    }
}
